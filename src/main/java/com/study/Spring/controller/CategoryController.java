package com.study.Spring.controller;

import com.study.Spring.dto.CategoryRequestDto;
import com.study.Spring.dto.CategoryResponseDto;
import com.study.Spring.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid CategoryRequestDto requestDto) {
        Long id = categoryService.createCategory(requestDto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDto requestDto) {
        Long updatedId = categoryService.updateCategory(id, requestDto);
        return ResponseEntity.ok(updatedId);
    }
}