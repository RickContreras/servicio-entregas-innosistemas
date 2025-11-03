package service;

import java.util.List;
import org.springframework.stereotype.Service;
import domain.Delivery;
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
        DeliveryValidator.validateForSave(delivery);
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery findById(Long id) {
        DeliveryValidator.validateId(id);
        return findDeliveryOrThrow(id);
    }

    @Override
    public Delivery update(Delivery delivery) {
        DeliveryValidator.validateForUpdate(delivery);
        ensureDeliveryExists(delivery.getId());
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDelivery(Long id, Delivery delivery) {
        DeliveryValidator.validateId(id);
        DeliveryValidator.validateNotNull(delivery);

        Delivery existingDelivery = findDeliveryOrThrow(id);
        updateDeliveryFields(existingDelivery, delivery);

        return deliveryRepository.save(existingDelivery);
    }

    @Override
    public void deleteById(Long id) {
        DeliveryValidator.validateId(id);
        ensureDeliveryExists(id);
        deliveryRepository.deleteById(id);
    }

    @Override
    public List<Delivery> findByProjectId(Integer projectId) {
        DeliveryValidator.validateProjectId(projectId);
        return deliveryRepository.findByProjectId(projectId);
    }

    // Métodos privados de utilidad para reducir duplicación

    private Delivery findDeliveryOrThrow(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con id: " + id));
    }

    private void ensureDeliveryExists(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entrega no encontrada con id: " + id);
        }
    }

    private void updateDeliveryFields(Delivery existing, Delivery updated) {
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setFileUrl(updated.getFileUrl());
        existing.setProjectId(updated.getProjectId());
    }
}
