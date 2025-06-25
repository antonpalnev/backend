package com.example.demo.dto;

import com.example.demo.entity.Pharmacy;

public class PharmacyResponse {
    private Long id;
    private String address;
    private String phone;

    public PharmacyResponse(Pharmacy pharmacy) {
        this.id = pharmacy.getId();
        this.address = pharmacy.getAddress();
        this.phone = pharmacy.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}