package service;

import java.util.List;
import domain.Delivery;

public interface DeliveryService {
    Delivery save(Delivery delivery);

    List<Delivery> findAll();

    List<Delivery> findByProjectId(Integer projectId);

    Delivery findById(Long id);

    Delivery update(Delivery delivery);

    void deleteById(Long id);
}
