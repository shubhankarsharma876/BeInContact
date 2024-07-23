package com.example.demo.Repository;

import com.example.demo.model.Message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface MessageRepository extends MongoRepository<Message,String> {
    List<Message> findByConversationId(String conversationId);
}
