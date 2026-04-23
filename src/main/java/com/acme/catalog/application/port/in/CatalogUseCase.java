package com.acme.catalog.application.port.in;

import com.acme.catalog.domain.CatalogItem;
import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada (driving port).
 * Define QUÉ puede hacer el mundo exterior con el catálogo.
 * El controlador REST implementará esto.
 */
public interface CatalogUseCase {

    List<CatalogItem> findAll();

    CatalogItem findById(UUID id);

    CatalogItem create(CreateItemCommand command);

    void delete(UUID id);
}

