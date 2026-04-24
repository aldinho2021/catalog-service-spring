package com.acme.catalog.adapters.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {

    private final ObjectMapper objectMapper;

    public UserCreatedConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "user-created", groupId = "catalog-service")
    public void consume(String payload) {
        try {
            UserCreatedEvent event = objectMapper.readValue(payload, UserCreatedEvent.class);
            System.out.println("EVENT user-created recibido -> " + event);
        } catch (Exception e) {
            throw new RuntimeException("Error processing event", e);
        }
    }
}
