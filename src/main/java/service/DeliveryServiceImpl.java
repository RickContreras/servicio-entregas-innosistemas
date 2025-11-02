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
        if (delivery == null) {
            throw new BadRequestException("La entrega no puede ser nula");
        }
        if (delivery.getTitle() == null || delivery.getTitle().trim().isEmpty()) {
            throw new BadRequestException("El título de la entrega es obligatorio");
        }
        if (delivery.getProject_id() == null) {
            throw new BadRequestException("El id del equipo es obligatorio");
        }
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery findById(Long id) {
        if (id == null) {
            throw new BadRequestException("El id no puede ser nulo");
        }
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con id: " + id));
    }

    @Override
    public Delivery update(Delivery delivery) {
        if (delivery == null || delivery.getId() == null) {
            throw new BadRequestException("La entrega y el id no pueden ser nulos");
        }
        if (delivery.getTitle() == null || delivery.getTitle().trim().isEmpty()) {
            throw new BadRequestException("El título de la entrega es obligatorio");
        }
        if (delivery.getProject_id() == null) {
            throw new BadRequestException("El id del equipo es obligatorio");
        }
        if (!deliveryRepository.existsById(delivery.getId())) {
            throw new ResourceNotFoundException("Entrega no encontrada con id: " + delivery.getId());
        }
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new BadRequestException("El id no puede ser nulo");
        }
        if (!deliveryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entrega no encontrada con id: " + id);
        }
        deliveryRepository.deleteById(id);
    }


    public List<Delivery> findByProjectId(Integer projectId) {
        if (projectId == null) {
            throw new BadRequestException("El id del proyecto no puede ser nulo");
        }
        return deliveryRepository.findByProjectId(projectId);
    }
}
