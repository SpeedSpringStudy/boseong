package com.study.Spring.service;

import com.study.Spring.dto.ProductRequestDto;
import com.study.Spring.dto.ProductResponseDto;
import com.study.Spring.entity.Product;
import com.study.Spring.entity.Category;
import com.study.Spring.repository.ProductRepository;
import com.study.Spring.vo.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService; // 추가

    public Slice<ProductResponseDto> getAll(Pageable pageable) {
        return productRepository.findAllBy(pageable)
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName().toString(),
                        product.getPrice()
                ));
    }

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName().toString(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

    public Long create(ProductRequestDto requestDto) {
        Category category = categoryService.findById(requestDto.categoryId()); // 카테고리 조회
        Product product = Product.builder()
                .name(new Name(requestDto.name()))
                .price(requestDto.price())
                .category(category)
                .build();
        return productRepository.save(product).getId();
    }

    public Long update(Long id, ProductRequestDto requestDto) {
        Category category = categoryService.findById(requestDto.categoryId()); // 카테고리 조회
        Product updated = Product.builder()
                .id(id)
                .name(new Name(requestDto.name()))
                .price(requestDto.price())
                .category(category)
                .build();
        return productRepository.save(updated).getId();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return new ProductResponseDto(product.getId(), product.getName().toString(), product.getPrice());
    }
}