package com.example.demo.dto;

import com.example.demo.entity.Stock;
import java.time.LocalDate;

public class StockResponse {
    private Long id;
    private Integer quantity;
    private Double price;
    private LocalDate arrivalDate;

    private Long pharmacyId;
    private String pharmacyAddress;

    private Long medicineId;
    private String medicineName;

    public StockResponse(Stock stock) {
        this.id = stock.getId();
        this.quantity = stock.getQuantity();
        this.price = stock.getPrice();
        this.arrivalDate = stock.getArrivalDate();

        if (stock.getPharmacy() != null) {
            this.pharmacyId = stock.getPharmacy().getId();
            this.pharmacyAddress = stock.getPharmacy().getAddress();
        }

        if (stock.getMedicine() != null) {
            this.medicineId = stock.getMedicine().getId();
            this.medicineName = stock.getMedicine().getName();
        }
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }
}