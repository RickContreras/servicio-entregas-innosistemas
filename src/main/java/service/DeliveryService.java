package service;

import java.util.List;
import domain.Delivery;

public interface DeliveryService {
    Delivery save (Delivery delivery);
    List<Delivery> findAll();
    Delivery findById(Long id);
    Delivery update(Delivery delivery);
    void deleteById(Long id);
}
