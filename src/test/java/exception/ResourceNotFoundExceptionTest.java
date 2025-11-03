package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ResourceNotFoundException - Pruebas Unitarias")
class ResourceNotFoundExceptionTest {

    @Test
    @DisplayName("Constructor con mensaje debe crear excepción correctamente")
    void testConstructorWithMessage() {
        // Arrange
        String message = "Recurso no encontrado";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("ResourceNotFoundException debe ser RuntimeException")
    void testExceptionIsRuntimeException() {
        // Arrange & Act
        ResourceNotFoundException exception = new ResourceNotFoundException("Test");

        // Assert
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Excepción debe ser lanzable y capturable")
    void testExceptionCanBeThrownAndCaught() {
        // Arrange
        String expectedMessage = "Entrega no encontrada con id: 123";

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException(expectedMessage);
        });

        try {
            throw new ResourceNotFoundException(expectedMessage);
        } catch (ResourceNotFoundException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    @DisplayName("Mensaje puede contener información de ID")
    void testExceptionMessageWithId() {
        // Arrange
        Long id = 999L;
        String message = "Entrega no encontrada con id: " + id;

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        // Assert
        assertEquals(message, exception.getMessage());
        assertTrue(exception.getMessage().contains("999"));
    }
}
