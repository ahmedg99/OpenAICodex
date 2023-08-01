package tn.spring.springboot.Services.Interfaces;

public interface IEmailService {
    public String sendSimpleMessage(String to, String subject, String text);
}
