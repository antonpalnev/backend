package com.example.demo.service;

import com.example.demo.entity.Pharmacy;
import com.example.demo.repository.PharmacyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PharmacyService {
    private final PharmacyRepository repository;

    public PharmacyService(PharmacyRepository repository) {
        this.repository = repository;
    }

    public Pharmacy create(Pharmacy pharmacy) {
        return repository.save(pharmacy);
    }

    public List<Pharmacy> findAll() {
        return repository.findAll();
    }

    public Pharmacy findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
    }

    public Pharmacy update(Long id, Pharmacy pharmacyDetails) {
        Pharmacy pharmacy = findById(id);
        pharmacy.setAddress(pharmacyDetails.getAddress());
        pharmacy.setPhone(pharmacyDetails.getPhone());
        return repository.save(pharmacy);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Pharmacy> findByAddressContaining(String address) {
        return repository.findByAddressContainingIgnoreCase(address);
    }
}