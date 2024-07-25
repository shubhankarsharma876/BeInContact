package com.example.demo.ExceptionHandling;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String usernameAlreadyExists) {
        super(usernameAlreadyExists);
    }
}
