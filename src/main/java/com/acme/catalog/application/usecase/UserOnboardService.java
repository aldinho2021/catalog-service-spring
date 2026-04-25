package com.acme.catalog.application.usecase;

import com.acme.catalog.application.port.in.UserOnboardUseCase;
import com.acme.catalog.application.port.out.CatalogRepository;
import com.acme.catalog.domain.CatalogItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserOnboardService implements UserOnboardUseCase {

    private final CatalogRepository catalogRepositoryPort;

    public UserOnboardService(CatalogRepository catalogRepositoryPort) {
        this.catalogRepositoryPort = catalogRepositoryPort;
    }

    @Override
    @Transactional
    public void onUserCreated(Long userId, String username, String email) {
        // Item inicial por defecto para usuario nuevo
        CatalogItem starter = new CatalogItem(
                UUID.randomUUID(),
                "Welcome Playlist",
                username,
                "Onboarding",
                2026
        );
        catalogRepositoryPort.save(starter);
        System.out.println("Item inicial guardado para " + username);
    }
}
