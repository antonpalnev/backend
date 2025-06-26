package com.example.demo.service;

import com.example.demo.dto.MedicineRequest;
import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    public Medicine getById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
    }

    public Medicine create(Medicine medicine) {
        return medicineRepository.saveAndFlush(medicine);
    }

    public Medicine update(Long id, Medicine updatedMedicine) {
        Medicine existing = getById(id);
        existing.setName(updatedMedicine.getName());
        existing.setCategory(updatedMedicine.getCategory());
        existing.setManufacturer(updatedMedicine.getManufacturer());
        existing.setForm(updatedMedicine.getForm());
        existing.setDosage(updatedMedicine.getDosage());
        return medicineRepository.saveAndFlush(existing);
    }

    public void delete(Long id) {
        medicineRepository.deleteById(id);
        medicineRepository.flush(); // <-- гарантирует, что удаление зафиксировано немедленно
    }

    public Medicine updateFromDto(Long id, MedicineRequest dto) {
        Medicine medicine = getById(id);
        medicine.setName(dto.getName());
        medicine.setCategory(dto.getCategory());
        medicine.setManufacturer(dto.getManufacturer());
        medicine.setForm(dto.getForm());
        medicine.setDosage(dto.getDosage());
        return medicineRepository.saveAndFlush(medicine);
    }

    public Medicine createFromDto(MedicineRequest dto) {
        Medicine medicine = new Medicine();
        medicine.setName(dto.getName());
        medicine.setCategory(dto.getCategory());
        medicine.setManufacturer(dto.getManufacturer());
        medicine.setForm(dto.getForm());
        medicine.setDosage(dto.getDosage());
        return medicineRepository.saveAndFlush(medicine);
    }
}