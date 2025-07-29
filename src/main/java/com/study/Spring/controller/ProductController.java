package com.study.Spring.controller;

import com.study.Spring.dto.ProductRequestDto;
import com.study.Spring.dto.ProductResponseDto;
import com.study.Spring.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Slice<ProductResponseDto> getAll(Pageable pageable) {
        return productService.getAll(pageable);
    }

    @PostMapping
    public Long create(@RequestBody @Valid ProductRequestDto requestDto) {
        return productService.create(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid ProductRequestDto requestDto) {
        return productService.update(id, requestDto);
    }
//    @PostMapping
//    public Long create(@RequestBody ProductRequestDto requestDto) {
//        return productService.create(requestDto);
//    }
//
//    @PutMapping("/{id}")
//    public Long update(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
//        return productService.update(id, requestDto);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}