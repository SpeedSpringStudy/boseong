package com.study.Spring.controller;

import com.study.Spring.dto.StockRequest;
import com.study.Spring.dto.StockResponse;
import com.study.Spring.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponse> createStock(@RequestBody StockRequest request) {
        return ResponseEntity.ok(stockService.createStock(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getStock(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStock(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponse> updateStock(@PathVariable Long id, @RequestBody StockRequest request) {
        return ResponseEntity.ok(stockService.updateStock(id, request));
    }
}