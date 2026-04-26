package com.acme.catalog.adapters.in.kafka;

import com.acme.catalog.application.port.in.UserOnboardUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {

    private final ObjectMapper objectMapper;
    private final UserOnboardUseCase userOnboardUseCase;

    public UserCreatedConsumer(ObjectMapper objectMapper, UserOnboardUseCase userOnboardUseCase) {
        this.objectMapper = objectMapper;
        this.userOnboardUseCase = userOnboardUseCase;
    }

    @KafkaListener(topics = "user-created", groupId = "catalog-service")
    public void consume(String payload) {
        try {
            UserCreatedEvent event = objectMapper.readValue(payload, UserCreatedEvent.class);
            System.out.println("Evento user-created recibido para userId=" + event.id());
            userOnboardUseCase.onUserCreated(event.id(), event.username(), event.email());
        } catch (Exception e) {
            throw new RuntimeException("Error processing user-created event", e);
        }
    }
}
