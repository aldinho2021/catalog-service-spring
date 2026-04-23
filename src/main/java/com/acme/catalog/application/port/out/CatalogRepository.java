package com.acme.catalog.application.port.out;

import com.acme.catalog.domain.CatalogItem;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida (driven port).
 * Define QUÉ necesita el dominio del almacenamiento.
 * La implementación JPA estará en adapters/out.
 */
public interface CatalogRepository {

    List<CatalogItem> findAll();

    Optional<CatalogItem> findById(UUID id);

    CatalogItem save(CatalogItem item);

    void deleteById(UUID id);

    boolean existsById(UUID id);
}

