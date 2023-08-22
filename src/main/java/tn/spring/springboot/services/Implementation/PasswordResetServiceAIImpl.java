package tn.spring.springboot.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IEmailService;
import tn.spring.springboot.entities.PasswordResetTokenAI;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.PasswordResetTokenAIRepository;
import tn.spring.springboot.repositories.UserRepository;

import java.util.Date;
import java.util.Random;

@Service
public class PasswordResetServiceAIImpl {

    private static final int EXPIRATION_TIME_IN_MINUTES = 60; // Token expiration time in minutes
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    private PasswordResetTokenAIRepository passwordResetTokenAIRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IEmailService emailService; // Implement this service to send emails

    // Generate a unique token and save it in the database with the associated user
    public void createPasswordResetToken(User user) {
        Random random = new Random();
        int randomCode = random.nextInt(9000) + 1000; // Generates a random number between 1000 and 9999 (inclusive)
        String token = Integer.toString(randomCode);
        Date expirationDate = calculateExpirationDate(EXPIRATION_TIME_IN_MINUTES);
        PasswordResetTokenAI passwordResetTokenAI = new PasswordResetTokenAI(user, token, expirationDate);
        passwordResetTokenAIRepository.save(passwordResetTokenAI);
        // Send the reset password email
        sendResetPasswordEmail(user.getEmail(), token);
    }
    // Send the reset password email to the user with the reset token
    private void sendResetPasswordEmail(String userEmail, String token) {
        // Implement this method to send the reset password email using the EmailService
        String resetPasswordLink = "YOUR TOKEN  " + token;
        String emailContent = "Click the following link to reset your password: " + resetPasswordLink;
        emailService.sendSimpleMessage(userEmail, "Reset Password", emailContent);
    }
    // Calculate the expiration date for the token based on the specified minutes
    private Date calculateExpirationDate(int minutes) {
        return new Date(System.currentTimeMillis() + (minutes * 60 * 1000));
    }
    // Verify the validity of the reset token
    public boolean isResetTokenValid(String token) {
        PasswordResetTokenAI passwordResetTokenAI = passwordResetTokenAIRepository.findByToken(token);
        return passwordResetTokenAI != null && !isTokenExpired(passwordResetTokenAI);
    }
    // Check if the token is expired
    private boolean isTokenExpired(PasswordResetTokenAI passwordResetTokenAI) {
        return passwordResetTokenAI.getExpirationDate().before(new Date());
    }
    // Update the user's password
    public void resetPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
         userRepository.save(user);
    }
    public User retrieveUserFromToken(String token ) {
        PasswordResetTokenAI passwordResetTokenAI = passwordResetTokenAIRepository.findByToken(token);
        return passwordResetTokenAI.getUser();

    }
}
