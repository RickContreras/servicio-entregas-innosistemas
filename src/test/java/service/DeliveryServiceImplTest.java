package service;

import domain.Delivery;
import exception.BadRequestException;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeliveryServiceImpl - Pruebas Unitarias")
class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    private Delivery validDelivery;

    @BeforeEach
    void setUp() {
        validDelivery = new Delivery();
        validDelivery.setId(1L);
        validDelivery.setTitle("Entrega 1");
        validDelivery.setDescription("Descripción de la entrega");
        validDelivery.setFileUrl("http://example.com/file.pdf");
        validDelivery.setCreatedAt(LocalDateTime.now());
        validDelivery.setProjectId(100);
    }

    @Test
    @DisplayName("Guardar entrega válida debe retornar la entrega guardada")
    void testSaveValidDelivery() {
        // Arrange
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(validDelivery);

        // Act
        Delivery result = deliveryService.save(validDelivery);

        // Assert
        assertNotNull(result);
        assertEquals("Entrega 1", result.getTitle());
        verify(deliveryRepository, times(1)).save(validDelivery);
    }

    @Test
    @DisplayName("Guardar entrega nula debe lanzar BadRequestException")
    void testSaveNullDelivery() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.save(null);
        });
        assertEquals("La entrega no puede ser nula", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Guardar entrega sin título debe lanzar BadRequestException")
    void testSaveDeliveryWithoutTitle() {
        // Arrange
        validDelivery.setTitle(null);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.save(validDelivery);
        });
        assertEquals("El título de la entrega es obligatorio", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Guardar entrega con título vacío debe lanzar BadRequestException")
    void testSaveDeliveryWithEmptyTitle() {
        // Arrange
        validDelivery.setTitle("   ");

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.save(validDelivery);
        });
        assertEquals("El título de la entrega es obligatorio", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Guardar entrega sin project_id debe lanzar BadRequestException")
    void testSaveDeliveryWithoutProjectId() {
        // Arrange
        validDelivery.setProjectId(null);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.save(validDelivery);
        });
        assertEquals("El id del equipo es obligatorio", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Obtener todas las entregas debe retornar lista de entregas")
    void testFindAll() {
        // Arrange
        Delivery delivery2 = new Delivery();
        delivery2.setId(2L);
        delivery2.setTitle("Entrega 2");
        delivery2.setProjectId(200);

        List<Delivery> deliveries = Arrays.asList(validDelivery, delivery2);
        when(deliveryRepository.findAll()).thenReturn(deliveries);

        // Act
        List<Delivery> result = deliveryService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(deliveryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar entrega por ID existente debe retornar la entrega")
    void testFindByIdExisting() {
        // Arrange
        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(validDelivery));

        // Act
        Delivery result = deliveryService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Entrega 1", result.getTitle());
        verify(deliveryRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Buscar entrega por ID inexistente debe lanzar ResourceNotFoundException")
    void testFindByIdNotFound() {
        // Arrange
        when(deliveryRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deliveryService.findById(999L);
        });
        assertEquals("Entrega no encontrada con id: 999", exception.getMessage());
        verify(deliveryRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Buscar entrega con ID nulo debe lanzar BadRequestException")
    void testFindByIdNull() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.findById(null);
        });
        assertEquals("El id no puede ser nulo", exception.getMessage());
        verify(deliveryRepository, never()).findById(any());
    }

    @Test
    @DisplayName("Actualizar entrega existente debe retornar la entrega actualizada")
    void testUpdateExistingDelivery() {
        // Arrange
        when(deliveryRepository.existsById(1L)).thenReturn(true);
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(validDelivery);

        // Act
        Delivery result = deliveryService.update(validDelivery);

        // Assert
        assertNotNull(result);
        assertEquals("Entrega 1", result.getTitle());
        verify(deliveryRepository, times(1)).existsById(1L);
        verify(deliveryRepository, times(1)).save(validDelivery);
    }

    @Test
    @DisplayName("Actualizar entrega nula debe lanzar BadRequestException")
    void testUpdateNullDelivery() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.update(null);
        });
        assertEquals("La entrega no puede ser nula", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Actualizar entrega con ID nulo debe lanzar BadRequestException")
    void testUpdateDeliveryWithNullId() {
        // Arrange
        validDelivery.setId(null);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.update(validDelivery);
        });
        assertEquals("El id no puede ser nulo", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Actualizar entrega sin título debe lanzar BadRequestException")
    void testUpdateDeliveryWithoutTitle() {
        // Arrange
        validDelivery.setTitle("");

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.update(validDelivery);
        });
        assertEquals("El título de la entrega es obligatorio", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Actualizar entrega sin project_id debe lanzar BadRequestException")
    void testUpdateDeliveryWithoutProjectId() {
        // Arrange
        validDelivery.setProjectId(null);

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.update(validDelivery);
        });
        assertEquals("El id del equipo es obligatorio", exception.getMessage());
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Actualizar entrega inexistente debe lanzar ResourceNotFoundException")
    void testUpdateNonExistingDelivery() {
        // Arrange
        when(deliveryRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deliveryService.update(validDelivery);
        });
        assertEquals("Entrega no encontrada con id: 1", exception.getMessage());
        verify(deliveryRepository, times(1)).existsById(1L);
        verify(deliveryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Eliminar entrega existente debe completarse exitosamente")
    void testDeleteExistingDelivery() {
        // Arrange
        when(deliveryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(deliveryRepository).deleteById(1L);

        // Act
        deliveryService.deleteById(1L);

        // Assert
        verify(deliveryRepository, times(1)).existsById(1L);
        verify(deliveryRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Eliminar entrega con ID nulo debe lanzar BadRequestException")
    void testDeleteWithNullId() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.deleteById(null);
        });
        assertEquals("El id no puede ser nulo", exception.getMessage());
        verify(deliveryRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Eliminar entrega inexistente debe lanzar ResourceNotFoundException")
    void testDeleteNonExistingDelivery() {
        // Arrange
        when(deliveryRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            deliveryService.deleteById(999L);
        });
        assertEquals("Entrega no encontrada con id: 999", exception.getMessage());
        verify(deliveryRepository, times(1)).existsById(999L);
        verify(deliveryRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Buscar entregas por project_id debe retornar lista de entregas")
    void testFindByProjectId() {
        // Arrange
        List<Delivery> deliveries = Arrays.asList(validDelivery);
        when(deliveryRepository.findByProjectId(100)).thenReturn(deliveries);

        // Act
        List<Delivery> result = deliveryService.findByProjectId(100);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100, result.get(0).getProjectId());
        verify(deliveryRepository, times(1)).findByProjectId(100);
    }

    @Test
    @DisplayName("Buscar entregas con project_id nulo debe lanzar BadRequestException")
    void testFindByProjectIdNull() {
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            deliveryService.findByProjectId(null);
        });
        assertEquals("El id del equipo es obligatorio", exception.getMessage());
        verify(deliveryRepository, never()).findByProjectId(any());
    }
    @Test
    @DisplayName("updateDelivery debe actualizar campos y guardar")
    void testUpdateDelivery() {
        Delivery existing = new Delivery(1L, "Old", "OldDesc", "old/url", LocalDateTime.now(), 50);
        Delivery updated = new Delivery(null, "New", "NewDesc", "new/url", LocalDateTime.now(), 99);

        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(deliveryRepository.save(existing)).thenReturn(existing);

        Delivery result = deliveryService.updateDelivery(1L, updated);

        assertEquals("New", result.getTitle());
        assertEquals("NewDesc", result.getDescription());
        assertEquals("new/url", result.getFileUrl());
        assertEquals(99, result.getProjectId());

        verify(deliveryRepository, times(1)).save(existing);
    }

    @Test
    @DisplayName("updateDelivery debe lanzar excepción cuando id no existe")
    void testUpdateDeliveryNotFound() {
        when(deliveryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> deliveryService.updateDelivery(1L, validDelivery));
    }
}
