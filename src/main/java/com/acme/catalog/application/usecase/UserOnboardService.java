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
        if (catalogRepositoryPort.findBySourceUserId(userId).isPresent()) {
            System.out.println("Evento ya procesado para userId=" + userId);
            return;
        }

        CatalogItem starter = new CatalogItem(
                UUID.randomUUID(),
                "Welcome Playlist",
                username,
                "Onboarding",
                2026,
                userId
        );

        catalogRepositoryPort.save(starter);
        System.out.println("Onboarding aplicado para userId=" + userId);
    }
}
