package com.study.Spring.controller;

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
    public ResponseEntity<String> createStock(@RequestParam Long combinationId, @RequestParam int quantity) {
        stockService.createStock(combinationId, quantity);
        return ResponseEntity.ok("재고가 등록되었습니다.");
    }

    @PostMapping("/decrease")
    public ResponseEntity<String> decreaseStock(@RequestParam Long combinationId, @RequestParam int count) {
        stockService.decreaseStockWithRetry(combinationId, count);
        return ResponseEntity.ok("재고가 차감되었습니다.");
    }
}