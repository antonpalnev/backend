package com.example.demo.service;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicineService {
    private final MedicineRepository repository;

    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
    }

    public Medicine create(Medicine medicine) {
        return repository.save(medicine);
    }

    public List<Medicine> findAll() {
        return repository.findAll();
    }

    public Medicine findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
    }

    public Medicine update(Long id, Medicine medicineDetails) {
        Medicine medicine = findById(id);
        medicine.setName(medicineDetails.getName());
        medicine.setCategory(medicineDetails.getCategory());
        medicine.setManufacturer(medicineDetails.getManufacturer());
        medicine.setForm(medicineDetails.getForm());
        medicine.setDosage(medicineDetails.getDosage());
        return repository.save(medicine);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Medicine> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Medicine> findByNameContaining(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}