package com.acme.catalog.domain;

import java.util.UUID;

/**
 * Entidad de dominio - no depende de Spring ni de JPA.
 * Representa un libro/canción del catálogo.
 */
public class CatalogItem {

    private final UUID id;
    private String title;
    private String author;
    private String genre;
    private int year;

    public CatalogItem(UUID id, String title, String author, String genre, int year) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title requerido");
        if (author == null || author.isBlank()) throw new IllegalArgumentException("author requerido");
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    // Lógica de dominio
    public boolean isRecentRelease(int currentYear) {
        return currentYear - this.year <= 2;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
}

