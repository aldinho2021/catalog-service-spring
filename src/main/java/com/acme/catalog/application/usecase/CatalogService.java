package com.acme.catalog.application.usecase;

import com.acme.catalog.application.port.in.CatalogUseCase;
import com.acme.catalog.application.port.in.CreateItemCommand;
import com.acme.catalog.application.port.out.CatalogRepository;
import com.acme.catalog.domain.CatalogItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Caso de uso principal. Orquesta dominio + puertos.
 * No sabe nada de HTTP ni de JPA.
 */
@Service
public class CatalogService implements CatalogUseCase {

    private final CatalogRepository repository;

    public CatalogService(CatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CatalogItem> findAll() {
        return repository.findAll();
    }

    @Override
    public CatalogItem findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item no encontrado: " + id));
    }

    @Override
    public CatalogItem create(CreateItemCommand command) {
        CatalogItem item = new CatalogItem(
                UUID.randomUUID(),
                command.title(),
                command.author(),
                command.genre(),
                command.year()
        );
        return repository.save(item);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException("Item no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}

