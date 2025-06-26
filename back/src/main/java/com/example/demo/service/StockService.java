package com.example.demo.service;

import com.example.demo.dto.StockRequest;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.Pharmacy;
import com.example.demo.entity.Stock;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PharmacyRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    public StockService(StockRepository stockRepository,
                        PharmacyRepository pharmacyRepository,
                        MedicineRepository medicineRepository) {
        this.stockRepository = stockRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
    }

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    public Stock getById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public Stock create(Stock stock) {
        return stockRepository.saveAndFlush(stock);
    }

    public Stock update(Long id, Stock updatedStock) {
        Stock existing = getById(id);
        existing.setPharmacy(updatedStock.getPharmacy());
        existing.setMedicine(updatedStock.getMedicine());
        existing.setQuantity(updatedStock.getQuantity());
        existing.setPrice(updatedStock.getPrice());
        existing.setArrivalDate(updatedStock.getArrivalDate());
        return stockRepository.saveAndFlush(existing);
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
        stockRepository.flush();
    }

    public Stock createFromDto(StockRequest dto) {
        Pharmacy pharmacy = pharmacyRepository.findById(dto.getPharmacyId())
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
        Medicine medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        Stock stock = new Stock();
        stock.setPharmacy(pharmacy);
        stock.setMedicine(medicine);
        stock.setQuantity(dto.getQuantity());
        stock.setPrice(dto.getPrice());
        stock.setArrivalDate(dto.getArrivalDate());
        return stockRepository.saveAndFlush(stock);
    }

    public Stock updateFromDto(Long id, StockRequest dto) {
        Stock stock = getById(id);

        Pharmacy pharmacy = pharmacyRepository.findById(dto.getPharmacyId())
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
        Medicine medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        stock.setPharmacy(pharmacy);
        stock.setMedicine(medicine);
        stock.setQuantity(dto.getQuantity());
        stock.setPrice(dto.getPrice());
        stock.setArrivalDate(dto.getArrivalDate());
        return stockRepository.saveAndFlush(stock);
    }
}