package tn.spring.springboot.exceptions;


public class UserNotFound extends Exception {
    public UserNotFound(String errorMessage) {
        super(errorMessage);
    }
}
