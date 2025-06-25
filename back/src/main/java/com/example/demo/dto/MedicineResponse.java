package com.example.demo.dto;

import com.example.demo.entity.Medicine;

public class MedicineResponse {
    private Long id;
    private String name;
    private String category;
    private String manufacturer;
    private String form;
    private String dosage;

    public MedicineResponse(Medicine medicine) {
        this.id = medicine.getId();
        this.name = medicine.getName();
        this.category = medicine.getCategory();
        this.manufacturer = medicine.getManufacturer();
        this.form = medicine.getForm();
        this.dosage = medicine.getDosage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}