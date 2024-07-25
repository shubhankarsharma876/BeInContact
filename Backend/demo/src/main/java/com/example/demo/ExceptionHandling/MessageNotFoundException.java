package com.example.demo.ExceptionHandling;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(String s) {
        super(s);
    }
}
