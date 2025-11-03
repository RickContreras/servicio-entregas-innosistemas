package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Delivery - Pruebas Unitarias")
class DeliveryTest {

    @Test
    @DisplayName("Constructor sin parámetros debe crear instancia vacía")
    void testDefaultConstructor() {
        // Act
        Delivery delivery = new Delivery();

        // Assert
        assertNotNull(delivery);
        assertNull(delivery.getId());
        assertNull(delivery.getTitle());
        assertNull(delivery.getDescription());
        assertNull(delivery.getFileUrl());
        assertNull(delivery.getCreatedAt());
        assertNull(delivery.getProjectId());
    }

    @Test
    @DisplayName("Constructor con parámetros debe crear instancia con valores")
    void testParameterizedConstructor() {
        // Arrange
        Long id = 1L;
        String title = "Entrega 1";
        String description = "Descripción";
        String fileUrl = "http://example.com/file.pdf";
        LocalDateTime createdAt = LocalDateTime.now();
        Integer projectId = 100;

        // Act
        Delivery delivery = new Delivery(id, title, description, fileUrl, createdAt, projectId);

        // Assert
        assertEquals(id, delivery.getId());
        assertEquals(title, delivery.getTitle());
        assertEquals(description, delivery.getDescription());
        assertEquals(fileUrl, delivery.getFileUrl());
        assertEquals(createdAt, delivery.getCreatedAt());
        assertEquals(projectId, delivery.getProjectId());
    }

    @Test
    @DisplayName("Setters deben actualizar los valores correctamente")
    void testSetters() {
        // Arrange
        Delivery delivery = new Delivery();
        Long id = 1L;
        String title = "Título actualizado";
        String description = "Descripción actualizada";
        String fileUrl = "http://example.com/updated.pdf";
        LocalDateTime createdAt = LocalDateTime.now();
        Integer projectId = 200;

        // Act
        delivery.setId(id);
        delivery.setTitle(title);
        delivery.setDescription(description);
        delivery.setFileUrl(fileUrl);
        delivery.setCreatedAt(createdAt);
        delivery.setProjectId(projectId);

        // Assert
        assertEquals(id, delivery.getId());
        assertEquals(title, delivery.getTitle());
        assertEquals(description, delivery.getDescription());
        assertEquals(fileUrl, delivery.getFileUrl());
        assertEquals(createdAt, delivery.getCreatedAt());
        assertEquals(projectId, delivery.getProjectId());
    }

    @Test
    @DisplayName("Getters deben retornar los valores correctos")
    void testGetters() {
        // Arrange
        Long id = 1L;
        String title = "Entrega Test";
        Integer projectId = 300;
        Delivery delivery = new Delivery(id, title, "Desc", "url", LocalDateTime.now(), projectId);

        // Act & Assert
        assertEquals(id, delivery.getId());
        assertEquals(title, delivery.getTitle());
        assertEquals(projectId, delivery.getProjectId());
    }

    @Test
    @DisplayName("onCreate debe establecer created_at automáticamente si es null")
    void testOnCreateSetsCreatedAt() {
        // Arrange
        Delivery delivery = new Delivery();
        delivery.setTitle("Test");
        delivery.setProjectId(100);
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);

        // Act
        delivery.onCreate(); // Simula @PrePersist
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        // Assert
        assertNotNull(delivery.getCreatedAt());
        assertTrue(delivery.getCreatedAt().isAfter(before));
        assertTrue(delivery.getCreatedAt().isBefore(after));
    }

    @Test
    @DisplayName("onCreate no debe sobrescribir created_at si ya tiene valor")
    void testOnCreateDoesNotOverrideExistingCreatedAt() {
        // Arrange
        LocalDateTime existingDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        Delivery delivery = new Delivery();
        delivery.setCreatedAt(existingDate);

        // Act
        delivery.onCreate(); // Simula @PrePersist

        // Assert
        assertEquals(existingDate, delivery.getCreatedAt());
    }

    @Test
    @DisplayName("Delivery debe permitir valores nulos en campos opcionales")
    void testNullableFields() {
        // Arrange & Act
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setTitle("Título");
        delivery.setDescription(null);
        delivery.setFileUrl(null);
        delivery.setProjectId(100);

        // Assert
        assertNotNull(delivery.getId());
        assertNotNull(delivery.getTitle());
        assertNull(delivery.getDescription());
        assertNull(delivery.getFileUrl());
        assertNotNull(delivery.getProjectId());
    }

    @Test
    @DisplayName("Delivery debe permitir actualización de todos los campos")
    void testFieldUpdates() {
        // Arrange
        Delivery delivery = new Delivery(1L, "Original", "Desc", "url", LocalDateTime.now(), 100);

        // Act
        delivery.setTitle("Actualizado");
        delivery.setDescription("Nueva descripción");
        delivery.setFileUrl("http://new-url.com");
        delivery.setProjectId(200);

        // Assert
        assertEquals("Actualizado", delivery.getTitle());
        assertEquals("Nueva descripción", delivery.getDescription());
        assertEquals("http://new-url.com", delivery.getFileUrl());
        assertEquals(200, delivery.getProjectId());
    }

    @Test
    @DisplayName("Delivery debe manejar URLs de archivo correctamente")
    void testFileUrlHandling() {
        // Arrange
        String[] urls = {
                "http://example.com/file.pdf",
                "https://storage.example.com/documents/report.docx",
                "file:///local/path/document.txt",
                ""
        };

        // Act & Assert
        for (String url : urls) {
            Delivery delivery = new Delivery();
            delivery.setFileUrl(url);
            assertEquals(url, delivery.getFileUrl());
        }
    }

    @Test
    @DisplayName("Delivery debe manejar diferentes tipos de project_id")
    void testProjectIdHandling() {
        // Arrange
        Delivery delivery = new Delivery();

        // Act & Assert
        delivery.setProjectId(1);
        assertEquals(1, delivery.getProjectId());

        delivery.setProjectId(999999);
        assertEquals(999999, delivery.getProjectId());

        delivery.setProjectId(0);
        assertEquals(0, delivery.getProjectId());
    }
}
