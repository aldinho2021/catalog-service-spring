package com.acme.catalog.application.port.in;

public interface UserOnboardUseCase {
    void onUserCreated(Long userId, String username, String email);
}