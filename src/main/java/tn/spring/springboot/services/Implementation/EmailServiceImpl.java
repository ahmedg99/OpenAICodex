package tn.spring.springboot.services.Implementation;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public String sendSimpleMessage(String to, String subject, String text)  {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("springforfever@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            System.out.println("Email sent successfully to: " + to);
            return "The code was sent successfully";
        } catch (MailException e) {
            System.err.println("Failed to send email to: " + to);
            e.printStackTrace();
            return  "Failed to send email to: " + to;
        }
    }
}
