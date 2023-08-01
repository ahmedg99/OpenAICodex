package tn.spring.springboot.Exceptions;


public class UserNotFound extends Exception {
    public UserNotFound(String errorMessage) {
        super(errorMessage);
    }
}
