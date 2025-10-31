package controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import domain.Delivery;
import service.DeliveryService;

@RestController
//http://localhost:8080/api/deliveries
@RequestMapping("/api/deliveries")
public class DeliveryController {
    
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    //http://localhost:8080/api/deliveries
    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    //http://localhost:8080/api/deliveries
    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.findAll();
    }

    //http://localhost:8080/api/deliveries/{id}
    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

    //http://localhost:8080/api/deliveries/{id}
    @PutMapping("/{id}")
    public Delivery updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        Delivery deliveryDb = deliveryService.findById(id);
        deliveryDb.setTitle(delivery.getTitle());
        deliveryDb.setDescription(delivery.getDescription());
        deliveryDb.setFile_url(delivery.getFile_url());
        deliveryDb.setTeam_id(delivery.getTeam_id()); 
        return deliveryService.update(deliveryDb);
    }

    //http://localhost:8080/api/deliveries/{id}
    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteById(id);
    }

}
