package service;

import java.util.List;
import org.springframework.stereotype.Service;
import domain.Delivery;
import exception.BadRequestException;
import exception.ResourceNotFoundException;
import repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery save(Delivery delivery) {
        validateDeliveryForSave(delivery);
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery findById(Long id) {
        validateIdNotNull(id);
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con id: " + id));
    }

    @Override
    public Delivery update(Delivery delivery) {
        validateDeliveryForUpdate(delivery);
        validateDeliveryExists(delivery.getId());
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteById(Long id) {
        validateIdNotNull(id);
        validateDeliveryExists(id);
        deliveryRepository.deleteById(id);
    }

    public List<Delivery> findByProjectId(Integer projectId) {
        validateProjectIdNotNull(projectId);
        return deliveryRepository.findByProjectId(projectId);
    }

    // Métodos de validación privados para reducir complejidad ciclomática

    private void validateDeliveryForSave(Delivery delivery) {
        validateDeliveryNotNull(delivery);
        validateTitle(delivery.getTitle());
        validateProjectIdNotNull(delivery.getProjectId());
    }

    private void validateDeliveryForUpdate(Delivery delivery) {
        validateDeliveryNotNull(delivery);
        validateDeliveryIdNotNull(delivery);
        validateTitle(delivery.getTitle());
        validateProjectIdNotNull(delivery.getProjectId());
    }

    private void validateDeliveryNotNull(Delivery delivery) {
        if (delivery == null) {
            throw new BadRequestException("La entrega no puede ser nula");
        }
    }

    private void validateDeliveryIdNotNull(Delivery delivery) {
        if (delivery.getId() == null) {
            throw new BadRequestException("El id de la entrega no puede ser nulo");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new BadRequestException("El título de la entrega es obligatorio");
        }
    }

    private void validateProjectIdNotNull(Integer projectId) {
        if (projectId == null) {
            throw new BadRequestException("El id del equipo es obligatorio");
        }
    }

    private void validateIdNotNull(Long id) {
        if (id == null) {
            throw new BadRequestException("El id no puede ser nulo");
        }
    }

    private void validateDeliveryExists(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entrega no encontrada con id: " + id);
        }
    }
}
