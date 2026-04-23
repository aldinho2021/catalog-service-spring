# catalog-service-spring

Microservicio de catálogo - Spring Boot 3 con arquitectura hexagonal.

## Stack
- Java 21
- Spring Boot 3.2
- Spring Data JPA + PostgreSQL
- Maven

## Estructura hexagonal

```
domain/          → entidades y lógica de negocio pura
application/
  port/in/       → interfaces que expone el servicio (CatalogUseCase)
  port/out/      → interfaces que necesita del exterior (CatalogRepository)
  usecase/       → implementación de casos de uso (CatalogService)
adapters/
  in/web/        → controladores REST (CatalogController)
  out/persistence/ → implementación JPA (CatalogRepositoryAdapter)
```

## Regla clave
- `domain` no depende de nada externo
- `application` depende solo de `domain` y sus puertos
- `adapters` implementan los puertos, pueden usar Spring/JPA

## Levantar local

### Con PostgreSQL en Podman
```powershell
podman run -d --name postgres `
  -e POSTGRES_USER=postgres `
  -e POSTGRES_PASSWORD=postgres `
  -e POSTGRES_DB=recsys `
  -p 5432:5432 docker.io/postgres:16
```

### Compilar y ejecutar
```powershell
mvn clean package -DskipTests
java -jar target/catalog-service-spring-0.0.1-SNAPSHOT.jar
```

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/v1/catalog | Listar todos |
| GET | /api/v1/catalog/{id} | Buscar por id |
| POST | /api/v1/catalog | Crear ítem |
| DELETE | /api/v1/catalog/{id} | Eliminar ítem |
| GET | /actuator/health | Health check |

## Ejemplo POST

```json
POST /api/v1/catalog
{
  "title": "Clean Code",
  "author": "Robert Martin",
  "genre": "Tech",
  "year": 2008
}
```

## Tests

```powershell
mvn test
```

