package com.example.demo.service;

import com.example.demo.dto.MedicineRequest;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.Stock;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final StockRepository stockRepository;

    public MedicineService(MedicineRepository medicineRepository, StockRepository stockRepository) {
        this.medicineRepository = medicineRepository;
        this.stockRepository = stockRepository;
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

    // Главное изменение — каскадное удаление stock!
    public void delete(Long id) {
        List<Stock> stocks = stockRepository.findByMedicineId(id);
        stockRepository.deleteAll(stocks);
        stockRepository.flush();
        medicineRepository.deleteById(id);
        medicineRepository.flush();
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