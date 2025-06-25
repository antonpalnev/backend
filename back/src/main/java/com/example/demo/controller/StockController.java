package com.example.demo.controller;

import com.example.demo.dto.StockRequest;
import com.example.demo.dto.StockResponse;
import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockResponse> getAllStocks() {
        return stockService.getAll().stream()
                .map(StockResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StockResponse getStockById(@PathVariable Long id) {
        Stock stock = stockService.getById(id);
        return new StockResponse(stock);
    }

    @PostMapping
    public StockResponse createStock(@RequestBody StockRequest stockRequest) {
        Stock stock = stockService.createFromDto(stockRequest);
        return new StockResponse(stock);
    }

    @PutMapping("/{id}")
    public StockResponse updateStock(@PathVariable Long id, @RequestBody StockRequest stockRequest) {
        Stock stock = stockService.updateFromDto(id, stockRequest);
        return new StockResponse(stock);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.delete(id);
    }
}