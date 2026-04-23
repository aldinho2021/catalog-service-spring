package com.acme.catalog.adapters.in.web;

import com.acme.catalog.application.port.in.CatalogUseCase;
import com.acme.catalog.application.port.in.CreateItemCommand;
import com.acme.catalog.application.usecase.ItemNotFoundException;
import com.acme.catalog.domain.CatalogItem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final CatalogUseCase catalogUseCase;

    public CatalogController(CatalogUseCase catalogUseCase) {
        this.catalogUseCase = catalogUseCase;
    }

    @GetMapping
    public List<CatalogItemResponse> findAll() {
        return catalogUseCase.findAll().stream()
                .map(CatalogItemResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public CatalogItemResponse findById(@PathVariable UUID id) {
        return CatalogItemResponse.from(catalogUseCase.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatalogItemResponse create(@RequestBody @Valid CreateItemRequest request) {
        CreateItemCommand command = new CreateItemCommand(
                request.title(), request.author(), request.genre(), request.year()
        );
        return CatalogItemResponse.from(catalogUseCase.create(command));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        catalogUseCase.delete(id);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // --- DTOs internos del adaptador ---

    public record CreateItemRequest(
            @jakarta.validation.constraints.NotBlank String title,
            @jakarta.validation.constraints.NotBlank String author,
            String genre,
            int year
    ) {}

    public record CatalogItemResponse(UUID id, String title, String author, String genre, int year) {
        static CatalogItemResponse from(CatalogItem item) {
            return new CatalogItemResponse(
                    item.getId(), item.getTitle(), item.getAuthor(), item.getGenre(), item.getYear()
            );
        }
    }
}

