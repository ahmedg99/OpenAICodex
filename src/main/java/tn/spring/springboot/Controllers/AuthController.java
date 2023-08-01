package tn.spring.springboot.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.Exceptions.UserNotFound;
import tn.spring.springboot.Services.Implementation.FileLocationService;
import tn.spring.springboot.Services.Interfaces.IEmailService;
import tn.spring.springboot.Services.Interfaces.IServicePasswordResetToken;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/auth")
@RestController
@Slf4j
 public class AuthController {

     IServiceUser serviceUser ;
     IEmailService iEmailService ;
     IServicePasswordResetToken iServicePasswordResetToken ;

     public AuthController(IServiceUser serviceUser , IEmailService iEmailService , IServicePasswordResetToken iServicePasswordResetToken) {
          this.serviceUser = serviceUser ;
          this.iEmailService = iEmailService ;
          this.iServicePasswordResetToken = iServicePasswordResetToken ;
      }




    @PostMapping("/user/resetPassword")
    public ResponseEntity<?> resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) throws UserNotFound {
        User user = serviceUser.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFound( "We didn't find an account for that e-mail address.");
        }
        Random random = new Random();
        int min = 1000; // Minimum 4-digit number
        int max = 9999; // Maximum 4-digit number
        int randomCode = random.nextInt(max - min + 1) + min;
         String token = String.valueOf(randomCode);
        serviceUser.createPasswordResetTokenForUser(user, token);
        try {
        String msgg = iEmailService.sendSimpleMessage(userEmail, "Reset Password",
                 "hello "+  user.getUsername()  + "your verification code  = " + token);
        Response response = new Response(msgg);
        return new ResponseEntity<>(response, HttpStatus.OK)  ;
        } catch (Exception e) {
            e.printStackTrace();
            Response response = new Response("The code was not sent");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST) ;
        }

    }


    @PostMapping("/reset_password/{token}")
    public ResponseEntity<?> showResetPasswordForm(@PathVariable(value = "token") String token) {
        String msg;
        PasswordResetToken passwordResetToken = iServicePasswordResetToken.findByToken(token);

        User user  = serviceUser.findUserByEmail(passwordResetToken.getUser().getEmail()) ;
         if (user == null){
            msg = "invalid Token";
             Response  response = new Response(msg);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
         }
        else{
            if(passwordResetToken.getExpiryDate().before(new Date())){
                msg = "Token Expired";
                Response  response = new Response(msg);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{
                msg = "Token Valid";
                Response response = new Response(msg);
                iServicePasswordResetToken.givePermissionToResetPassword(passwordResetToken);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
         }
    }



    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword1 (@RequestBody Parametres parametres ){
        String msg = serviceUser.updatePassword(parametres.code, parametres.password);
        Response  response = new Response(msg);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private static class Parametres{
        String code;
        String password;

        public Parametres(String code, String password){
            this.code = code;
            this.password = password;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    public class Response {
         @Getter
            private String message;

        public Response(String msg) {
            this.message = msg;
        }
    }


}


