package com.acme.catalog.application.usecase;

import com.acme.catalog.application.port.in.CreateItemCommand;
import com.acme.catalog.application.port.out.CatalogRepository;
import com.acme.catalog.domain.CatalogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatalogServiceTest {

    private CatalogRepository repository;
    private CatalogService service;

    @BeforeEach
    void setUp() {
        repository = mock(CatalogRepository.class);
        service = new CatalogService(repository);
    }

    @Test
    void deberia_crear_item_con_uuid_nuevo() {
        CreateItemCommand command = new CreateItemCommand("Clean Code", "Robert Martin", "Tech", 2008);
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        CatalogItem result = service.create(command);

        assertNotNull(result.getId());
        assertEquals("Clean Code", result.getTitle());
        verify(repository).save(any());
    }

    @Test
    void deberia_lanzar_excepcion_cuando_no_existe() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> service.findById(id));
    }

    @Test
    void deberia_retornar_todos_los_items() {
        List<CatalogItem> items = List.of(
                new CatalogItem(UUID.randomUUID(), "Book A", "Author A", "Fiction", 2020),
                new CatalogItem(UUID.randomUUID(), "Book B", "Author B", "Tech", 2022)
        );
        when(repository.findAll()).thenReturn(items);

        assertEquals(2, service.findAll().size());
    }
}

