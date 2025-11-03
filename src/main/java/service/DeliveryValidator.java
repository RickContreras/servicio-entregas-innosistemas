package service;

import domain.Delivery;
import exception.BadRequestException;

/**
 * Clase utilitaria para centralizar las validaciones de Delivery.
 * Ayuda a reducir la complejidad ciclomática al extraer todas las validaciones.
 */
public class DeliveryValidator {

    private DeliveryValidator() {
        // Constructor privado para prevenir instanciación
        throw new IllegalStateException("Utility class");
    }

    public static void validateForSave(Delivery delivery) {
        validateNotNull(delivery);
        validateTitle(delivery.getTitle());
        validateProjectId(delivery.getProjectId());
    }

    public static void validateForUpdate(Delivery delivery) {
        validateNotNull(delivery);
        validateId(delivery.getId());
        validateTitle(delivery.getTitle());
        validateProjectId(delivery.getProjectId());
    }

    public static void validateNotNull(Delivery delivery) {
        if (delivery == null) {
            throw new BadRequestException("La entrega no puede ser nula");
        }
    }

    public static void validateId(Long id) {
        if (id == null) {
            throw new BadRequestException("El id no puede ser nulo");
        }
    }

    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new BadRequestException("El título de la entrega es obligatorio");
        }
    }

    public static void validateProjectId(Integer projectId) {
        if (projectId == null) {
            throw new BadRequestException("El id del equipo es obligatorio");
        }
    }
}
