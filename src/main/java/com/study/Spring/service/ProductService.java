package com.study.Spring.service;

import com.study.Spring.dto.ProductRequestDto;
import com.study.Spring.dto.ProductResponseDto;
import com.study.Spring.entity.Product;
import com.study.Spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponseDto(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public Long create(ProductRequestDto requestDto) {
        Product product = new Product(null, requestDto.name(), requestDto.price());
        return productRepository.save(product);
    }

    public Long update(Long id, ProductRequestDto requestDto) {
        Product updated = new Product(null, requestDto.name(), requestDto.price());
        return productRepository.update(id, updated);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}