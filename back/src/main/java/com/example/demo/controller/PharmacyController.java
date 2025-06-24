package com.example.demo.controller;

import com.example.demo.entity.Pharmacy;
import com.example.demo.service.PharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {
    private final PharmacyService service;

    public PharmacyController(PharmacyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pharmacy> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pharmacy getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Pharmacy create(@RequestBody Pharmacy pharmacy) {
        return service.create(pharmacy);
    }

    @PutMapping("/{id}")
    public Pharmacy update(@PathVariable Long id, @RequestBody Pharmacy pharmacy) {
        return service.update(id, pharmacy);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Pharmacy> searchByAddress(@RequestParam String address) {
        return service.findByAddressContaining(address);
    }
}