package com.study.Spring.controller;

import com.study.Spring.entity.ProductComposition;
import com.study.Spring.service.ProductCompositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/compositions")
public class ProductCompositionController {

    private final ProductCompositionService compositionService;

    @PostMapping
    public ResponseEntity<Long> createComposition(@RequestParam Long productId, @RequestBody List<Long> optionValueIds) {
        Long id = compositionService.createComposition(productId, optionValueIds);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<ProductComposition>> getCompositions(@RequestParam Long productId) {
        return ResponseEntity.ok(compositionService.getCompositionsByProduct(productId));
    }
}