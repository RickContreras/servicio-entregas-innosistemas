package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ErrorResponse - Pruebas Unitarias")
class ErrorResponseTest {

    @Test
    @DisplayName("Constructor debe crear ErrorResponse correctamente")
    void testConstructor() {
        // Arrange
        int status = 404;
        String message = "Recurso no encontrado";

        // Act
        ErrorResponse errorResponse = new ErrorResponse(status, message);

        // Assert
        assertNotNull(errorResponse);
        assertEquals(status, errorResponse.getStatus());
        assertEquals(message, errorResponse.getMessage());
        assertNotNull(errorResponse.getTimestamp());
    }

    @Test
    @DisplayName("Timestamp debe ser generado automáticamente")
    void testTimestampAutoGeneration() {
        // Arrange
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);

        // Act
        ErrorResponse errorResponse = new ErrorResponse(500, "Error interno");
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        // Assert
        assertNotNull(errorResponse.getTimestamp());
        assertTrue(errorResponse.getTimestamp().isAfter(before));
        assertTrue(errorResponse.getTimestamp().isBefore(after));
    }

    @Test
    @DisplayName("Setters deben actualizar los valores correctamente")
    void testSetters() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse(400, "Error inicial");
        LocalDateTime newTimestamp = LocalDateTime.now();
        int newStatus = 500;
        String newMessage = "Nuevo mensaje de error";

        // Act
        errorResponse.setTimestamp(newTimestamp);
        errorResponse.setStatus(newStatus);
        errorResponse.setMessage(newMessage);

        // Assert
        assertEquals(newTimestamp, errorResponse.getTimestamp());
        assertEquals(newStatus, errorResponse.getStatus());
        assertEquals(newMessage, errorResponse.getMessage());
    }

    @Test
    @DisplayName("Getters deben retornar los valores correctos")
    void testGetters() {
        // Arrange
        int status = 404;
        String message = "No encontrado";
        ErrorResponse errorResponse = new ErrorResponse(status, message);

        // Act & Assert
        assertEquals(status, errorResponse.getStatus());
        assertEquals(message, errorResponse.getMessage());
        assertNotNull(errorResponse.getTimestamp());
    }

    @Test
    @DisplayName("ErrorResponse debe permitir diferentes códigos de estado HTTP")
    void testDifferentStatusCodes() {
        // Arrange & Act
        ErrorResponse error400 = new ErrorResponse(400, "Bad Request");
        ErrorResponse error404 = new ErrorResponse(404, "Not Found");
        ErrorResponse error500 = new ErrorResponse(500, "Internal Server Error");

        // Assert
        assertEquals(400, error400.getStatus());
        assertEquals(404, error404.getStatus());
        assertEquals(500, error500.getStatus());
    }

    @Test
    @DisplayName("ErrorResponse debe permitir mensaje vacío")
    void testEmptyMessage() {
        // Arrange & Act
        ErrorResponse errorResponse = new ErrorResponse(400, "");

        // Assert
        assertEquals("", errorResponse.getMessage());
    }

    @Test
    @DisplayName("ErrorResponse debe permitir mensaje nulo")
    void testNullMessage() {
        // Arrange & Act
        ErrorResponse errorResponse = new ErrorResponse(400, null);

        // Assert
        assertNull(errorResponse.getMessage());
    }
}
