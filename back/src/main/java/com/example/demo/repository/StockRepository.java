package com.example.demo.repository;

import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByPharmacyId(Long pharmacyId);
    List<Stock> findByMedicineId(Long medicineId);
    List<Stock> findByPriceLessThan(double price);
    List<Stock> findByQuantityGreaterThan(int quantity);
}