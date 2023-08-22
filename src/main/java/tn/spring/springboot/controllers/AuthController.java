package tn.spring.springboot.controllers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.exceptions.UserNotFound;
import tn.spring.springboot.services.Interfaces.IEmailService;
import tn.spring.springboot.services.Interfaces.IServicePasswordResetToken;
import tn.spring.springboot.services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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


