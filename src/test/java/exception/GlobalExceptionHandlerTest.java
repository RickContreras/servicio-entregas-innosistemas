package exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GlobalExceptionHandler - Pruebas Unitarias")
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("handleResourceNotFound debe retornar 404 con mensaje de error")
    void testHandleResourceNotFound() {
        // Arrange
        String errorMessage = "Entrega no encontrada con id: 123";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleResourceNotFound(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().getStatus());
        assertEquals(errorMessage, response.getBody().getMessage());
    }

    @Test
    @DisplayName("handleBadRequest debe retornar 400 con mensaje de error")
    void testHandleBadRequest() {
        // Arrange
        String errorMessage = "El título de la entrega es obligatorio";
        BadRequestException exception = new BadRequestException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleBadRequest(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getStatus());
        assertEquals(errorMessage, response.getBody().getMessage());
    }

    @Test
    @DisplayName("handleGenericException debe retornar 500 con mensaje de error")
    void testHandleGenericException() {
        // Arrange
        String errorMessage = "Error inesperado";
        Exception exception = new Exception(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGenericException(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(500, response.getBody().getStatus());
        assertTrue(response.getBody().getMessage().contains("Error interno del servidor"));
        assertTrue(response.getBody().getMessage().contains(errorMessage));
    }

    @Test
    @DisplayName("handleGenericException debe manejar RuntimeException")
    void testHandleGenericRuntimeException() {
        // Arrange
        String errorMessage = "Error de runtime";
        RuntimeException exception = new RuntimeException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGenericException(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getBody().getStatus());
    }

    @Test
    @DisplayName("handleGenericException debe manejar NullPointerException")
    void testHandleNullPointerException() {
        // Arrange
        NullPointerException exception = new NullPointerException("Referencia nula");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGenericException(exception);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getBody().getStatus());
        assertTrue(response.getBody().getMessage().contains("Error interno del servidor"));
    }
}
