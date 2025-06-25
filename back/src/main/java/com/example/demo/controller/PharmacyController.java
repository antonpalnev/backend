package com.example.demo.controller;

import com.example.demo.dto.PharmacyRequest;
import com.example.demo.dto.PharmacyResponse;
import com.example.demo.entity.Pharmacy;
import com.example.demo.service.PharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public List<PharmacyResponse> getAllPharmacies() {
        return pharmacyService.getAll().stream()
                .map(PharmacyResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PharmacyResponse getPharmacyById(@PathVariable Long id) {
        Pharmacy pharmacy = pharmacyService.getById(id);
        return new PharmacyResponse(pharmacy);
    }

    @PostMapping
    public PharmacyResponse createPharmacy(@RequestBody PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyService.createFromDto(pharmacyRequest);
        return new PharmacyResponse(pharmacy);
    }

    @PutMapping("/{id}")
    public PharmacyResponse updatePharmacy(@PathVariable Long id, @RequestBody PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyService.updateFromDto(id, pharmacyRequest);
        return new PharmacyResponse(pharmacy);
    }

    @DeleteMapping("/{id}")
    public void deletePharmacy(@PathVariable Long id) {
        pharmacyService.delete(id);
    }
}