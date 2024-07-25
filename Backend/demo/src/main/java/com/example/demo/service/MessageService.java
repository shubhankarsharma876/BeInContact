package com.example.demo.service;

import com.example.demo.model.Message;

import java.util.List;

public interface  MessageService {
    Message createMessage(Message message);
    Message getMessageById(String id);
    List<Message> getMessagesForConversation(String conversationId);
    List<Message> getMessagesForUser(String userId);
    Message updateMessage(Message message);
    void deleteMessage(String id);

}
