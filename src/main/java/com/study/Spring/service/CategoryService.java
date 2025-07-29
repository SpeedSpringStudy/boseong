package com.study.Spring.service;

import com.study.Spring.dto.CategoryRequestDto;
import com.study.Spring.dto.CategoryResponseDto;
import com.study.Spring.entity.Category;
import com.study.Spring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createCategory(CategoryRequestDto dto) {
        if (categoryRepository.existsByName(dto.name())) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        Category category = Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
        return categoryRepository.save(category).getId();
    }

    @Transactional
    public Long updateCategory(Long id, CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        category.setName(dto.name());
        category.setDescription(dto.description());
        return categoryRepository.save(category).getId();
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryResponseDto(c.getId(), c.getName(), c.getDescription()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}