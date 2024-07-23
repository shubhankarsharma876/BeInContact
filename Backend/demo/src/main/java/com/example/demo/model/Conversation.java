package com.example.demo.model;

import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

public class Conversation {
    @Id
    private String id;
    private List<String> participantsId;
    private String type;
    private String lastMessageId;
    private Instant lastActivityTime;
}
