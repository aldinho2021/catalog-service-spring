package com.acme.catalog.adapters.in.web;

import com.acme.catalog.application.port.in.CatalogUseCase;
import com.acme.catalog.application.port.in.CreateItemCommand;
import com.acme.catalog.application.usecase.ItemNotFoundException;
import com.acme.catalog.domain.CatalogItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Catalog", description = "Operaciones CRUD del catalogo")
@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final CatalogUseCase catalogUseCase;

    public CatalogController(CatalogUseCase catalogUseCase) {
        this.catalogUseCase = catalogUseCase;
    }

    @Operation(summary = "Listar catalogo", description = "Devuelve todos los items del catalogo")
    @ApiResponse(responseCode = "200", description = "Listado obtenido")
    @GetMapping
    public List<CatalogItemResponse> findAll() {
        return catalogUseCase.findAll().stream()
                .map(CatalogItemResponse::from)
                .toList();
    }

    @Operation(summary = "Buscar item por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item encontrado"),
            @ApiResponse(responseCode = "404", description = "Item no encontrado")
    })
    @GetMapping("/{id}")
    public CatalogItemResponse findById(@PathVariable UUID id) {
        return CatalogItemResponse.from(catalogUseCase.findById(id));
    }

    @Operation(summary = "Crear item")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Item creado"),
            @ApiResponse(responseCode = "400", description = "Request invalido",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatalogItemResponse create(@RequestBody @Valid CreateItemRequest request) {
        CreateItemCommand command = new CreateItemCommand(
                request.title(), request.author(), request.genre(), request.year()
        );
        return CatalogItemResponse.from(catalogUseCase.create(command));
    }

    @Operation(summary = "Eliminar item por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Item eliminado"),
            @ApiResponse(responseCode = "404", description = "Item no encontrado")
    })
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

