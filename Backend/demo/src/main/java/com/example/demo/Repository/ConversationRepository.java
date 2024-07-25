package com.example.demo.Repository;

import com.example.demo.model.Conversation;
import com.example.demo.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;



public interface ConversationRepository extends MongoRepository<Conversation,String> {
    List<Conversation> findByParticipantIdsContaining(String userId);
}
