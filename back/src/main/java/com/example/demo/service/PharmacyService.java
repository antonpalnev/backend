package com.example.demo.service;

import com.example.demo.dto.PharmacyRequest;
import com.example.demo.entity.Pharmacy;
import com.example.demo.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getById(Long id) {
        return pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
    }

    public Pharmacy create(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    public Pharmacy update(Long id, Pharmacy updatedPharmacy) {
        Pharmacy existing = getById(id);
        existing.setAddress(updatedPharmacy.getAddress());
        existing.setPhone(updatedPharmacy.getPhone());
        return pharmacyRepository.save(existing);
    }

    public void delete(Long id) {
        pharmacyRepository.deleteById(id);
    }

    public Pharmacy createFromDto(PharmacyRequest dto) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setPhone(dto.getPhone());
        return pharmacyRepository.save(pharmacy);
    }

    public Pharmacy updateFromDto(Long id, PharmacyRequest dto) {
        Pharmacy pharmacy = getById(id);
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setPhone(dto.getPhone());
        return pharmacyRepository.save(pharmacy);
    }
}