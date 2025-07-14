package com.study.Spring.service;

import com.study.Spring.dto.ProductRequestDto;
import com.study.Spring.dto.ProductResponseDto;
import com.study.Spring.entity.Product;
import com.study.Spring.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<ProductResponseDto> getAll() {
        return productDao.findAll()
                .stream()
                .map(product -> new ProductResponseDto(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public Long create(ProductRequestDto requestDto) {
        validateProductName(requestDto.name());
        Product product = Product.builder()
                .name(requestDto.name())
                .price(requestDto.price())
                .build();
        return productDao.save(product);
    }


    public Long update(Long id, ProductRequestDto requestDto) {
        Product updated = Product.builder()
                .id(id)
                .name(requestDto.name())
                .price(requestDto.price())
                .build();
        return productDao.update(id, updated);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    public ProductResponseDto findById(Long id) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
    }

    private void validateProductName(String name) {
        if (name.contains("카카오")) {
            throw new IllegalArgumentException("“카카오”가 포함된 상품 이름은 MD 승인 후 사용 가능합니다.");
        }
    }
}