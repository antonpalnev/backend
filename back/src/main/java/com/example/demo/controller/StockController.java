package com.example.demo.controller;

import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping
    public List<Stock> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Stock getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return service.create(stock);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable Long id, @RequestBody Stock stock) {
        return service.update(id, stock);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Остальные методы остаются без изменений
    @GetMapping("/pharmacy/{pharmacyId}")
    public List<Stock> getByPharmacy(@PathVariable Long pharmacyId) {
        return service.findByPharmacyId(pharmacyId);
    }

    @GetMapping("/medicine/{medicineId}")
    public List<Stock> getByMedicine(@PathVariable Long medicineId) {
        return service.findByMedicineId(medicineId);
    }

    @GetMapping("/search/price")
    public List<Stock> searchByPrice(@RequestParam double price) {
        return service.findByPriceLessThan(price);
    }

    @GetMapping("/search/quantity")
    public List<Stock> searchByQuantity(@RequestParam int quantity) {
        return service.findByQuantityGreaterThan(quantity);
    }
}