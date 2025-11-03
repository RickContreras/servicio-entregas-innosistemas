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
        assertNull(delivery.getFile_url());
        assertNull(delivery.getCreated_at());
        assertNull(delivery.getProject_id());
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
        assertEquals(fileUrl, delivery.getFile_url());
        assertEquals(createdAt, delivery.getCreated_at());
        assertEquals(projectId, delivery.getProject_id());
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
        delivery.setFile_url(fileUrl);
        delivery.setCreated_at(createdAt);
        delivery.setProject_id(projectId);

        // Assert
        assertEquals(id, delivery.getId());
        assertEquals(title, delivery.getTitle());
        assertEquals(description, delivery.getDescription());
        assertEquals(fileUrl, delivery.getFile_url());
        assertEquals(createdAt, delivery.getCreated_at());
        assertEquals(projectId, delivery.getProject_id());
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
        assertEquals(projectId, delivery.getProject_id());
    }

    @Test
    @DisplayName("onCreate debe establecer created_at automáticamente si es null")
    void testOnCreateSetsCreatedAt() {
        // Arrange
        Delivery delivery = new Delivery();
        delivery.setTitle("Test");
        delivery.setProject_id(100);
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);

        // Act
        delivery.onCreate(); // Simula @PrePersist
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        // Assert
        assertNotNull(delivery.getCreated_at());
        assertTrue(delivery.getCreated_at().isAfter(before));
        assertTrue(delivery.getCreated_at().isBefore(after));
    }

    @Test
    @DisplayName("onCreate no debe sobrescribir created_at si ya tiene valor")
    void testOnCreateDoesNotOverrideExistingCreatedAt() {
        // Arrange
        LocalDateTime existingDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        Delivery delivery = new Delivery();
        delivery.setCreated_at(existingDate);

        // Act
        delivery.onCreate(); // Simula @PrePersist

        // Assert
        assertEquals(existingDate, delivery.getCreated_at());
    }

    @Test
    @DisplayName("Delivery debe permitir valores nulos en campos opcionales")
    void testNullableFields() {
        // Arrange & Act
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setTitle("Título");
        delivery.setDescription(null);
        delivery.setFile_url(null);
        delivery.setProject_id(100);

        // Assert
        assertNotNull(delivery.getId());
        assertNotNull(delivery.getTitle());
        assertNull(delivery.getDescription());
        assertNull(delivery.getFile_url());
        assertNotNull(delivery.getProject_id());
    }

    @Test
    @DisplayName("Delivery debe permitir actualización de todos los campos")
    void testFieldUpdates() {
        // Arrange
        Delivery delivery = new Delivery(1L, "Original", "Desc", "url", LocalDateTime.now(), 100);

        // Act
        delivery.setTitle("Actualizado");
        delivery.setDescription("Nueva descripción");
        delivery.setFile_url("http://new-url.com");
        delivery.setProject_id(200);

        // Assert
        assertEquals("Actualizado", delivery.getTitle());
        assertEquals("Nueva descripción", delivery.getDescription());
        assertEquals("http://new-url.com", delivery.getFile_url());
        assertEquals(200, delivery.getProject_id());
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
            delivery.setFile_url(url);
            assertEquals(url, delivery.getFile_url());
        }
    }

    @Test
    @DisplayName("Delivery debe manejar diferentes tipos de project_id")
    void testProjectIdHandling() {
        // Arrange
        Delivery delivery = new Delivery();

        // Act & Assert
        delivery.setProject_id(1);
        assertEquals(1, delivery.getProject_id());

        delivery.setProject_id(999999);
        assertEquals(999999, delivery.getProject_id());

        delivery.setProject_id(0);
        assertEquals(0, delivery.getProject_id());
    }
}
