package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.Services.Implementation.PasswordResetServiceAIImpl;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.User;

@RequestMapping("/aipasswordreset")
@RestController
public class PasswordResetController {

    @Autowired
    private PasswordResetServiceAIImpl passwordResetService;
    @Autowired
    private IServiceUser serviceUser ;
    // Display the reset password page
    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam(name = "token") String token, Model model) {
        if (passwordResetService.isResetTokenValid(token)) {
            model.addAttribute("token", token);
            return "reset-password"; // Assuming you have a Thymeleaf (or other template engine) template named "reset-password.html"
        } else {
            // Handle invalid token case, e.g., redirect to an error page or show an error message
            return "redirect:/invalid-token";
        }
    }
    // Process the password update
    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam(name = "token") String token,
                                       @RequestParam(name = "newPassword") String newPassword) {
        if (passwordResetService.isResetTokenValid(token)) {
            User user = passwordResetService.retrieveUserFromToken(token);
                     passwordResetService.resetPassword(user, newPassword);
            // Redirect to a success page or a login page with a success message
            return "redirect:/login?resetSuccess";
        } else {
            // Handle invalid token case, e.g., redirect to an error page or show an error message
            return "redirect:/invalid-token";
        }
    }

    @PostMapping("/send-reset-email")
    public String sendPasswordResetEmail(@RequestParam(name = "email") String userEmail) {
        User user = serviceUser.findUserByEmail(userEmail);
        if (user != null) {
            passwordResetService.createPasswordResetToken(user);
            // Redirect to a success page or show a success message indicating that the reset email has been sent
            return "redirect:/reset-email-sent";
        } else {
            // Handle the case when the provided email does not exist in the system
            // Redirect to an error page or show an error message
            return "redirect:/invalid-email";
        }
    }



}
