package tn.spring.springboot.services.Interfaces;

public interface IEmailService {
    public String sendSimpleMessage(String to, String subject, String text);
}
