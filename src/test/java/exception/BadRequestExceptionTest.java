package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BadRequestException - Pruebas Unitarias")
class BadRequestExceptionTest {

    @Test
    @DisplayName("Constructor con mensaje debe crear excepción correctamente")
    void testConstructorWithMessage() {
        // Arrange
        String message = "Este es un error de solicitud incorrecta";

        // Act
        BadRequestException exception = new BadRequestException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Constructor con mensaje y causa debe crear excepción correctamente")
    void testConstructorWithMessageAndCause() {
        // Arrange
        String message = "Error de validación";
        Throwable cause = new IllegalArgumentException("Argumento inválido");

        // Act
        BadRequestException exception = new BadRequestException(message, cause);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
    }

    @Test
    @DisplayName("BadRequestException debe ser RuntimeException")
    void testExceptionIsRuntimeException() {
        // Arrange & Act
        BadRequestException exception = new BadRequestException("Test");

        // Assert
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Excepción debe ser lanzable y capturable")
    void testExceptionCanBeThrownAndCaught() {
        // Arrange
        String expectedMessage = "Datos inválidos";

        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(expectedMessage);
        });

        try {
            throw new BadRequestException(expectedMessage);
        } catch (BadRequestException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
