package com.acme.catalog.adapters.in.kafka;

public record UserCreatedEvent(Long id, String username, String email) {}

