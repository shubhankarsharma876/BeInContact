package com.example.demo.ExceptionHandling;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(String conversationNotFound) {
        super(conversationNotFound);
    }
}
