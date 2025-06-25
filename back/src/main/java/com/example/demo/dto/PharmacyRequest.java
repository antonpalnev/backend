package com.example.demo.dto;

public class PharmacyRequest {
    private String address;
    private String phone;

    public PharmacyRequest() {
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