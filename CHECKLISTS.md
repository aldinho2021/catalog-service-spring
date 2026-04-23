# ✅ CHECKLIST DE PRUEBAS

## 🎯 Elige tu camino

```
┌─────────────────────────────────────────────────────────────┐
│  QUIERO PROBAR AHORA MISMO (5 minutos)                      │
└─────────────────────────────────────────────────────────────┘

  □ 1. Abre PowerShell en D:\aldo\catalog-service-spring
  □ 2. Ejecuta: mvn spring-boot:run
  □ 3. Espera a ver: "Started CatalogApplication in X.XXX seconds"
  □ 4. Abre otra terminal (en la MISMA carpeta)
  □ 5. Ejecuta: .\test-api.ps1
  □ 6. Verás: ✅ Servidor está UP
  □ 7. Verás: ✅ Item creado
  □ 8. Verás: ✅ Item recuperado
  □ 9. Verás: ✅ Listar todos
  □ 10. Verás: ✅ Error 404 capturado
  □ 11. Verás: ✅ Validación capturada
  □ 12. Verás: ✅ Item eliminado
  □ 13. Verás: === PRUEBAS COMPLETADAS ===
  
  ✅ ¡ÉXITO! Tu servicio funciona

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  QUIERO ENTENDER CÓMO FUNCIONA (10 minutos)                 │
└─────────────────────────────────────────────────────────────┘

  □ 1. Lee: START-HERE.md (2 min)
  □ 2. Abre PowerShell en D:\aldo\catalog-service-spring
  □ 3. Ejecuta: mvn clean test
  □ 4. Verás: Tests run: 3, Failures: 0, SUCCESS ✅
  □ 5. Lee: README-TESTING.md (5 min)
  □ 6. Entiendes: Los 4 métodos de prueba
  □ 7. Entiendes: Cómo funcionan los endpoints
  □ 8. (Opcional) Prueba CURL manualmente
  
  ✅ ¡ÉXITO! Entiendes tu servicio

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  QUIERO PROFUNDIZAR (20 minutos)                            │
└─────────────────────────────────────────────────────────────┘

  □ 1. Lee: INDEX.md
  □ 2. Lee: HOW-TO-TEST.md (completo)
  □ 3. Lee: CURL-EXAMPLES.md
  □ 4. Ejecuta servidor: mvn spring-boot:run
  □ 5. Prueba manualmente cada CURL ejemplo
  □ 6. Abre Postman y descargar collection
  □ 7. Import: Catalog-Service-API.postman_collection.json
  □ 8. Prueba cada endpoint en Postman
  □ 9. Lee: Sección Troubleshooting
  □ 10. Entiendes: Toda la arquitectura

  ✅ ¡ÉXITO! Eres experto en el proyecto

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  QUIERO USAR POSTMAN (7 minutos)                            │
└─────────────────────────────────────────────────────────────┘

  □ 1. Descargar Postman: https://www.postman.com/
  □ 2. Abre Postman
  □ 3. Click: File → Import
  □ 4. Selecciona: Catalog-Service-API.postman_collection.json
  □ 5. Click: Import
  □ 6. En PowerShell: mvn spring-boot:run
  □ 7. En Postman: Click en "Health Check" → Send
  □ 8. Verás: {"status":"UP"} ✅
  □ 9. Click en "Crear nuevo item" → Send
  □ 10. Verás: {"id":"...", "title":"Clean Code", ...} ✅
  □ 11. Sigue con otros endpoints
  
  ✅ ¡ÉXITO! Pruebas completadas en GUI

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  QUIERO USAR CURL (8 minutos)                               │
└─────────────────────────────────────────────────────────────┘

  □ 1. En PowerShell: mvn spring-boot:run
  □ 2. Abre otra PowerShell
  □ 3. Copia de CURL-EXAMPLES.md:
       
       curl -X POST http://localhost:8081/api/v1/catalog \
         -H "Content-Type: application/json" \
         -d '{"title":"Clean Code","author":"Robert...","genre":"Tech","year":2008}'
  
  □ 4. Pégalo en PowerShell y presiona Enter
  □ 5. Verás: {"id":"550e8400-...", "title":"Clean Code", ...} ✅
  □ 6. Copia el "id" (después de "id":")
  □ 7. Ejecuta: curl http://localhost:8081/api/v1/catalog/{id}
  □ 8. Verás: El item que creaste ✅
  □ 9. Ejecuta: curl http://localhost:8081/api/v1/catalog
  □ 10. Verás: Todos los items en un array ✅
  □ 11. (Opcional) Prueba eliminar, errores, etc.
  
  ✅ ¡ÉXITO! CURL funcionando

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  ALGO NO FUNCIONA (Troubleshooting)                         │
└─────────────────────────────────────────────────────────────┘

  Problema: "mvn not found"
  □ Solución: Instala Maven o usa: .\mvnw spring-boot:run

  Problema: "Port 8081 already in use"
  □ Solución: mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8082"

  Problema: "java: command not found"
  □ Solución: Instala Java 21
  □ Verifica: java -version

  Problema: "Test failed"
  □ Solución: mvn clean test (recompila)

  Problema: "CURL no funciona en Windows"
  □ Solución: Usa PowerShell 5+
  □ O: Usa WSL / Git Bash

  Problema: "Postman no funciona"
  □ Solución: Verifica que base_url = http://localhost:8081
  □ Verifica que servidor está corriendo

  ✅ Problema resuelto

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  VOY A MOSTRAR MI PROYECTO A ALGUIEN (15 minutos)           │
└─────────────────────────────────────────────────────────────┘

  Preparación:
  □ 1. Ejecuta: mvn clean compile (para que esté limpio)
  □ 2. Ten listo: mvn spring-boot:run
  □ 3. Ten listo: .\test-api.ps1
  □ 4. Ten listo: Postman abierto
  □ 5. Ten listo: CURL en PowerShell

  Presentación:
  □ 1. Muestra: La estructura del proyecto
  □ 2. Explica: Arquitectura Hexagonal (domain, application, adapters)
  □ 3. Ejecuta: mvn spring-boot:run
  □ 4. Ejecuta: .\test-api.ps1
  □ 5. Muestra: Todos los tests pasando
  □ 6. (Opcional) Ejecuta CURL manualmente
  □ 7. (Opcional) Prueba en Postman
  □ 8. Explica: Cómo está validado
  □ 9. Explica: Cómo maneja errores

  ✅ ¡Impresionante! Tu proyecto está bien

═════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────┐
│  QUIERO MEJORAR MI PROYECTO (30+ minutos)                   │
└─────────────────────────────────────────────────────────────┘

  Ideas:
  □ 1. Agregar validación de año: if (year < 1000) throw ...
  □ 2. Agregar búsqueda por título: findByTitle(String title)
  □ 3. Agregar actualización (PUT)
  □ 4. Agregar paginación: Page<CatalogItem> findAll(Pageable)
  □ 5. Agregar Swagger/OpenAPI (springdoc-openapi)
  □ 6. Agregar logs: logger.info(...)
  □ 7. Agregar más tests: findByTitleTest, updateTest, etc.
  □ 8. Agregar Docker: docker build / docker run
  □ 9. Agregar CI/CD: GitHub Actions
  □ 10. Agregar métricas: Micrometer / Prometheus

  ✅ ¡Proyecto mejorado!

═════════════════════════════════════════════════════════════════
```

---

## 📝 Mi Recomendación

**Para empezar:** Sigue el checklist "QUIERO PROBAR AHORA MISMO"

**Para aprender:** Sigue el checklist "QUIERO ENTENDER CÓMO FUNCIONA"

**Para dominar:** Sigue el checklist "QUIERO PROFUNDIZAR"

---

## 🎯 TL;DR - Lo más rápido

```bash
# Terminal 1
mvn spring-boot:run

# Terminal 2 (después que veas "Started CatalogApplication")
.\test-api.ps1
```

¡Eso es! ✅


