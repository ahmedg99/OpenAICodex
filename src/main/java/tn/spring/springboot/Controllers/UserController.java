package tn.spring.springboot.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.Services.Implementation.FileLocationService;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Image;
import tn.spring.springboot.entities.Role;
import tn.spring.springboot.entities.User;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    IServiceUser serviceUser ;
    @Autowired
    FileLocationService iFileLocationService ;


    @GetMapping(value = "/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return serviceUser.getAllUsers();
    }

    @GetMapping(value = "/getAllUsers/{offset}/{pageSize}")
    @ResponseBody
    public Page<User> getAllUsers(@PathVariable int offset , @PathVariable int pageSize) {
        return serviceUser.getAllUsers(offset, pageSize);
    }
    @GetMapping(value = "/getUserByUsername/{username}")
    @ResponseBody
    public User getAllUsers(@PathVariable String username) {
        return serviceUser.getUser(username);
    }


    @PostMapping(value = "/add" ,  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> addUser( @RequestParam String name ,
                                      @RequestParam String username ,
                                      @RequestParam String password,
                                      @RequestParam String email,
                                      @RequestParam List<String> roles,
                                      @RequestBody() MultipartFile image ) throws Exception {

        // add image
        try {
            Image savedImage = iFileLocationService.save(image.getBytes(), image.getOriginalFilename());
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add").toUriString());
            return ResponseEntity.created(uri).body(serviceUser.createUser(name, username,password,email,roles, savedImage));
        } catch (Exception e) {
            return ResponseEntity.ok().body("erro : " + e.getMessage());

        }




    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = serviceUser.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/addRoleToUser/{username}/{rolename}")
    @ResponseBody
    public void addRoleToUser(@PathVariable String username , @PathVariable String rolename) {
        serviceUser.addRoleToUser(username, rolename);
    }

    @GetMapping(value = "/refreshToken")
    @ResponseBody
    public void  refreshToken(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = serviceUser.getUser(username);
                String[] roles = decodedJWT.getClaim("role").asArray(String.class);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("role", user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                //afficher les tokens dans Body
                Map<String , String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                log.error("Error logging in : {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message" , exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
         } else {
            throw new  RuntimeException("refresh token is missing") ;
        }
    }






    }
