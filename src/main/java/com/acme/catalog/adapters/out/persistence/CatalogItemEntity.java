package com.acme.catalog.adapters.out.persistence;

import com.acme.catalog.domain.CatalogItem;
import jakarta.persistence.*;
import java.util.UUID;

/**
 * Entidad JPA - vive solo en el adaptador de persistencia.
 * El dominio NO conoce esta clase.
 */
@Entity
@Table(name = "catalog_items")
class CatalogItemEntity {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String genre;

    private int year;

    protected CatalogItemEntity() {}

    static CatalogItemEntity fromDomain(CatalogItem item) {
        CatalogItemEntity e = new CatalogItemEntity();
        // Evita IdentifierGenerationException cuando llega id null
        e.id = item.getId() != null ? item.getId() : UUID.randomUUID();
        e.title = item.getTitle();
        e.author = item.getAuthor();
        e.genre = item.getGenre();
        e.year = item.getYear();
        return e;
    }

    CatalogItem toDomain() {
        return new CatalogItem(id, title, author, genre, year);
    }
}