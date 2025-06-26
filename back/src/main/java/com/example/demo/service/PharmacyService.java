package com.example.demo.service;

import com.example.demo.dto.PharmacyRequest;
import com.example.demo.entity.Pharmacy;
import com.example.demo.entity.Stock;
import com.example.demo.repository.PharmacyRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final StockRepository stockRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository, StockRepository stockRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.stockRepository = stockRepository;
    }

    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getById(Long id) {
        return pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
    }

    public Pharmacy create(Pharmacy pharmacy) {
        return pharmacyRepository.saveAndFlush(pharmacy);
    }

    public Pharmacy update(Long id, Pharmacy updatedPharmacy) {
        Pharmacy existing = getById(id);
        existing.setAddress(updatedPharmacy.getAddress());
        existing.setPhone(updatedPharmacy.getPhone());
        return pharmacyRepository.saveAndFlush(existing);
    }

    // Главное изменение — каскадное удаление stock!
    public void delete(Long id) {
        List<Stock> stocks = stockRepository.findByPharmacyId(id);
        stockRepository.deleteAll(stocks);
        stockRepository.flush();
        pharmacyRepository.deleteById(id);
        pharmacyRepository.flush();
    }

    public Pharmacy updateFromDto(Long id, PharmacyRequest dto) {
        Pharmacy pharmacy = getById(id);
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setPhone(dto.getPhone());
        return pharmacyRepository.saveAndFlush(pharmacy);
    }

    public Pharmacy createFromDto(PharmacyRequest dto) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setPhone(dto.getPhone());
        return pharmacyRepository.saveAndFlush(pharmacy);
    }
}