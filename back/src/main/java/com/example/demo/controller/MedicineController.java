package com.example.demo.controller;

import com.example.demo.entity.Medicine;
import com.example.demo.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineService service;

    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medicine> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Medicine getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Medicine create(@RequestBody Medicine medicine) {
        return service.create(medicine);
    }

    @PutMapping("/{id}")
    public Medicine update(@PathVariable Long id, @RequestBody Medicine medicine) {
        return service.update(id, medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/category/{category}")
    public List<Medicine> getByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/search")
    public List<Medicine> searchByName(@RequestParam String name) {
        return service.findByNameContaining(name);
    }
}