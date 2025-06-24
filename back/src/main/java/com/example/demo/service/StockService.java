package com.example.demo.service;

import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class StockService {
    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public Stock create(Stock stock) {
        return repository.save(stock);
    }

    public List<Stock> findAll() {
        return repository.findAll();
    }

    public Stock findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
    }

    public Stock update(Long id, Stock stockDetails) {
        Stock stock = findById(id);
        stock.setQuantity(stockDetails.getQuantity());
        stock.setPrice(stockDetails.getPrice());
        stock.setArrivalDate(stockDetails.getArrivalDate());
        return repository.save(stock);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Остальные методы остаются без изменений
    public List<Stock> findByPharmacyId(Long pharmacyId) {
        return repository.findByPharmacyId(pharmacyId);
    }

    public List<Stock> findByMedicineId(Long medicineId) {
        return repository.findByMedicineId(medicineId);
    }

    public List<Stock> findByPriceLessThan(double price) {
        return repository.findByPriceLessThan(price);
    }

    public List<Stock> findByQuantityGreaterThan(int quantity) {
        return repository.findByQuantityGreaterThan(quantity);
    }
}