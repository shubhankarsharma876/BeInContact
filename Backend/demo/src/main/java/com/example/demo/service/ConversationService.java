package com.example.demo.service;

import com.example.demo.model.Conversation;

import java.util.List;

public interface ConversationService {
    Conversation createConversation(Conversation conversation);
    Conversation getConversationsById(String id);
    List<Conversation> getConversationsForUser(String userId);
    Conversation updateConversation(Conversation conversation);
    void deleteConservation(String id);
}
