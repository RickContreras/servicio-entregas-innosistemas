package service;

import domain.Delivery;
import exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DeliveryValidator - Pruebas Unitarias")
class DeliveryValidatorTest {

    @Test
    @DisplayName("validateForSave con delivery válido no lanza excepción")
    void testValidateForSaveWithValidDelivery() {
        // Arrange
        Delivery delivery = new Delivery(null, "Título", "Descripción", "url", LocalDateTime.now(), 1);

        // Act & Assert
        assertDoesNotThrow(() -> DeliveryValidator.validateForSave(delivery));
    }

    @Test
    @DisplayName("validateForSave con delivery nulo lanza BadRequestException")
    void testValidateForSaveWithNullDelivery() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForSave(null);
        });
        assertEquals("La entrega no puede ser nula", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "   ", "\t", "\n" })
    @DisplayName("validateForSave con título inválido lanza BadRequestException")
    void testValidateForSaveWithInvalidTitle(String title) {
        // Arrange
        Delivery delivery = new Delivery(null, title, "Descripción", "url", LocalDateTime.now(), 1);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForSave(delivery);
        });
        assertEquals("El título de la entrega es obligatorio", exception.getMessage());
    }

    @Test
    @DisplayName("validateForSave con projectId nulo lanza BadRequestException")
    void testValidateForSaveWithNullProjectId() {
        // Arrange
        Delivery delivery = new Delivery(null, "Título", "Descripción", "url", LocalDateTime.now(), null);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForSave(delivery);
        });
        assertEquals("El id del equipo es obligatorio", exception.getMessage());
    }

    @Test
    @DisplayName("validateForUpdate con delivery válido no lanza excepción")
    void testValidateForUpdateWithValidDelivery() {
        // Arrange
        Delivery delivery = new Delivery(1L, "Título", "Descripción", "url", LocalDateTime.now(), 1);

        // Act & Assert
        assertDoesNotThrow(() -> DeliveryValidator.validateForUpdate(delivery));
    }

    @Test
    @DisplayName("validateForUpdate con delivery nulo lanza BadRequestException")
    void testValidateForUpdateWithNullDelivery() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForUpdate(null);
        });
        assertEquals("La entrega no puede ser nula", exception.getMessage());
    }

    @Test
    @DisplayName("validateForUpdate con id nulo lanza BadRequestException")
    void testValidateForUpdateWithNullId() {
        // Arrange
        Delivery delivery = new Delivery(null, "Título", "Descripción", "url", LocalDateTime.now(), 1);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForUpdate(delivery);
        });
        assertEquals("El id no puede ser nulo", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "   ", "\t", "\n" })
    @DisplayName("validateForUpdate con título inválido lanza BadRequestException")
    void testValidateForUpdateWithInvalidTitle(String title) {
        // Arrange
        Delivery delivery = new Delivery(1L, title, "Descripción", "url", LocalDateTime.now(), 1);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForUpdate(delivery);
        });
        assertEquals("El título de la entrega es obligatorio", exception.getMessage());
    }

    @Test
    @DisplayName("validateForUpdate con projectId nulo lanza BadRequestException")
    void testValidateForUpdateWithNullProjectId() {
        // Arrange
        Delivery delivery = new Delivery(1L, "Título", "Descripción", "url", LocalDateTime.now(), null);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateForUpdate(delivery);
        });
        assertEquals("El id del equipo es obligatorio", exception.getMessage());
    }

    @Test
    @DisplayName("validateNotNull con delivery válido no lanza excepción")
    void testValidateNotNullWithValidDelivery() {
        // Arrange
        Delivery delivery = new Delivery();

        // Act & Assert
        assertDoesNotThrow(() -> DeliveryValidator.validateNotNull(delivery));
    }

    @Test
    @DisplayName("validateNotNull con delivery nulo lanza BadRequestException")
    void testValidateNotNullWithNullDelivery() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateNotNull(null);
        });
        assertEquals("La entrega no puede ser nula", exception.getMessage());
    }

    @Test
    @DisplayName("validateId con id válido no lanza excepción")
    void testValidateIdWithValidId() {
        // Act & Assert
        assertDoesNotThrow(() -> DeliveryValidator.validateId(1L));
    }

    @Test
    @DisplayName("validateId con id nulo lanza BadRequestException")
    void testValidateIdWithNullId() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateId(null);
        });
        assertEquals("El id no puede ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("validateTitle con título válido no lanza excepción")
    void testValidateTitleWithValidTitle() {
        // Act & Assert
        assertDoesNotThrow(() -> DeliveryValidator.validateTitle("Título válido"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "   ", "\t", "\n", "  \t\n  " })
    @DisplayName("validateTitle con título inválido lanza BadRequestException")
    void testValidateTitleWithInvalidTitle(String title) {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateTitle(title);
        });
        assertEquals("El título de la entrega es obligatorio", exception.getMessage());
    }

    @Test
    @DisplayName("validateProjectId con projectId válido no lanza excepción")
    void testValidateProjectIdWithValidProjectId() {
        // Act & Assert
        assertDoesNotThrow(() -> DeliveryValidator.validateProjectId(1));
    }

    @Test
    @DisplayName("validateProjectId con projectId nulo lanza BadRequestException")
    void testValidateProjectIdWithNullProjectId() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            DeliveryValidator.validateProjectId(null);
        });
        assertEquals("El id del equipo es obligatorio", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor de DeliveryValidator lanza IllegalStateException")
    void testConstructorThrowsException() throws Exception {
        // Arrange
        var constructor = DeliveryValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // Act & Assert
        var exception = assertThrows(java.lang.reflect.InvocationTargetException.class,
                constructor::newInstance);

        // La reflexión envuelve la excepción en InvocationTargetException
        assertTrue(exception.getCause() instanceof IllegalStateException);
        assertEquals("Utility class", exception.getCause().getMessage());
    }

    @Test
    @DisplayName("validateTitle con título con espacios al inicio y final pero contenido válido no lanza excepción")
    void testValidateTitleWithValidContentAndSpaces() {
        // Act & Assert - El título se valida con trim(), así que espacios al
        // inicio/final no deberían causar error
        assertDoesNotThrow(() -> DeliveryValidator.validateTitle("  Título válido  "));
    }
}
