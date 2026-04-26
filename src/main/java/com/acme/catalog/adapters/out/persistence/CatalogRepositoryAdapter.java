package com.acme.catalog.adapters.out.persistence;

import com.acme.catalog.application.port.out.CatalogRepository;
import com.acme.catalog.domain.CatalogItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación del puerto de salida usando JPA.
 * El dominio solo ve la interfaz CatalogRepository.
 */
@Component
public class CatalogRepositoryAdapter implements CatalogRepository {

    private final CatalogItemJpaRepository jpa;

    public CatalogRepositoryAdapter(CatalogItemJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<CatalogItem> findAll() {
        return jpa.findAll().stream().map(CatalogItemEntity::toDomain).toList();
    }

    @Override
    public Optional<CatalogItem> findById(UUID id) {
        return jpa.findById(id).map(CatalogItemEntity::toDomain);
    }

    @Override
    @Transactional
    public CatalogItem save(CatalogItem item) {
        return jpa.save(CatalogItemEntity.fromDomain(item)).toDomain();
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpa.existsById(id);
    }

    @Override
    public Optional<CatalogItem> findBySourceUserId(Long sourceUserId) {
        return jpa.findBySourceUserId(sourceUserId).map(CatalogItemEntity::toDomain);
    }
}

