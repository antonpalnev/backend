package com.example.demo.repository;

import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByPharmacyId(Long pharmacyId);
    List<Stock> findByMedicineId(Long medicineId);
}