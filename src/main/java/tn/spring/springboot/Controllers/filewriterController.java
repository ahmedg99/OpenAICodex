package tn.spring.springboot.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.Services.Implementation.FileLocationService;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Image;
import tn.spring.springboot.entities.Role;
import tn.spring.springboot.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/fileWriter")
@RestController
@Slf4j
public class filewriterController {


    @Autowired
    FileLocationService iFileLocationService ;


    @GetMapping(value = "/getAllUsers")
    @ResponseBody
    public void  generaterclass(String content , String filename) throws IOException {
         iFileLocationService.writeStringToFile(content , filename) ;
    }

    }
