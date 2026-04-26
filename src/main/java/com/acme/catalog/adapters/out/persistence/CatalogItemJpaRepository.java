package com.acme.catalog.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CatalogItemJpaRepository extends JpaRepository<CatalogItemEntity, UUID> {
    Optional<CatalogItemEntity> findBySourceUserId(Long sourceUserId);
}

