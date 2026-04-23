package com.acme.catalog.application.port.in;

/**
 * Command para crear un ítem. Encapsula los datos de entrada.
 */
public record CreateItemCommand(
        String title,
        String author,
        String genre,
        int year
) {}

