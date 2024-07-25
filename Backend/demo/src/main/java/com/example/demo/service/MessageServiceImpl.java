package com.example.demo.service;

import com.example.demo.ExceptionHandling.ConversationNotFoundException;
import com.example.demo.ExceptionHandling.MessageNotFoundException;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.model.Conversation;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Override
    public Message createMessage(Message message) {
        message.setTimestamp(Instant.now());
        Message savedMessage = messageRepository.save(message);

        Conversation conversation = conversationRepository.findById(message.getConversationId())
                .orElseThrow(()->new ConversationNotFoundException("Conversation not found"));
        conversation.setLastMessageId(message.getId());
        conversation.setLastActivityTime(Instant.now());
        conversationRepository.save(conversation);
        return savedMessage;
    }

    @Override
    public Message getMessageById(String id) {
        return messageRepository.findById(id)
                .orElseThrow(()->new MessageNotFoundException("Message not found with id: " + id) );
    }

    @Override
    public List<Message> getMessagesForConversation(String conversationId) {
        return messageRepository.findByConversationIdOrderByTimestampAsc(conversationId);
    }

    @Override
    public List<Message> getMessagesForUser(String userId) {
        List<Conversation> userConversations = conversationRepository.findByParticipantIdsContaining(userId);
        List<String> conversationIds = userConversations.stream()
                .map(Conversation::getId)
                .collect(Collectors.toList());
        return messageRepository.findByConversationIdInOrderByTimestampDesc(conversationIds);
    }

    @Override
    public Message updateMessage(Message message) {
        if (!messageRepository.existsById(message.getId())) {
            throw new MessageNotFoundException("Message not found with id: " + message.getId());
        }
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(String id) {
        if (!messageRepository.existsById(id)) {
            throw new MessageNotFoundException("Message not found with id: " + id);
        }
        messageRepository.deleteById(id);
    }
}
