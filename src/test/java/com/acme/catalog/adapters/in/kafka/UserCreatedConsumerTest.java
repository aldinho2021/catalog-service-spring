package com.acme.catalog.adapters.in.kafka;

import com.acme.catalog.CatalogServiceSpringApplication;
import com.acme.catalog.application.port.out.CatalogRepository;
import com.acme.catalog.domain.CatalogItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CatalogServiceSpringApplication.class)
class UserCreatedConsumerTest {

    @Autowired
    private UserCreatedConsumer consumer;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanUp() {
        List<CatalogItem> items = catalogRepository.findAll();
        for (CatalogItem item : items) {
            catalogRepository.deleteById(item.getId());
        }
    }

    @Test
    void shouldCreateStarterCatalogItemWhenUserCreatedEventArrives() throws Exception {
        UserCreatedEvent event = new UserCreatedEvent(100L, "olaf", "olaf@gmail.com");
        String payload = objectMapper.writeValueAsString(event);

        consumer.consume(payload);

        List<CatalogItem> items = catalogRepository.findAll();

        assertEquals(1, items.size());
        CatalogItem created = items.get(0);

        assertEquals("Welcome Playlist", created.getTitle());
        assertEquals("olaf", created.getAuthor());
        assertEquals("Onboarding", created.getGenre());
        assertEquals(2026, created.getYear());
        assertEquals(100L, created.getSourceUserId());
    }

    @Test
    void shouldBeIdempotentWhenSameEventArrivesTwice() throws Exception {
        UserCreatedEvent event = new UserCreatedEvent(200L, "olafito", "olafito@gmail.com");
        String payload = objectMapper.writeValueAsString(event);

        consumer.consume(payload);
        consumer.consume(payload);

        List<CatalogItem> items = catalogRepository.findAll();

        assertEquals(1, items.size());
        assertEquals(200L, items.get(0).getSourceUserId());
    }
}