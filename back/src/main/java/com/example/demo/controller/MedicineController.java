package com.example.demo.controller;

import com.example.demo.dto.MedicineRequest;
import com.example.demo.dto.MedicineResponse;
import com.example.demo.entity.Medicine;
import com.example.demo.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public List<MedicineResponse> getAllMedicines() {
        return medicineService.getAll().stream()
                .map(MedicineResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MedicineResponse getMedicineById(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        return new MedicineResponse(medicine);
    }

    @PostMapping
    public MedicineResponse createMedicine(@RequestBody MedicineRequest medicineRequest) {
        Medicine medicine = medicineService.createFromDto(medicineRequest);
        return new MedicineResponse(medicine);
    }

    @PutMapping("/{id}")
    public MedicineResponse updateMedicine(@PathVariable Long id, @RequestBody MedicineRequest medicineRequest) {
        Medicine medicine = medicineService.updateFromDto(id, medicineRequest);
        return new MedicineResponse(medicine);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        medicineService.delete(id);
    }
}