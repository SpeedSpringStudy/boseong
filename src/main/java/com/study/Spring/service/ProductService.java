package com.study.Spring.service;

import com.study.Spring.dto.ProductRequestDto;
import com.study.Spring.dto.ProductResponseDto;
import com.study.Spring.entity.Product;
//import com.study.Spring.dao.ProductDao;
import com.study.Spring.repository.ProductRepository;
import com.study.Spring.vo.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ProductService {

//    private final ProductDao productDao;
    private final ProductRepository productRepository;

    public Page<ProductResponseDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> new ProductResponseDto(product.getId(), product.getName().toString(), product.getPrice()));
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
//        validateProductName(requestDto.name());
        Product product = Product.builder()
                .name(new Name(requestDto.name()))
                .price(requestDto.price())
                .build();
        return productRepository.save(product).getId();
    }


    public Long update(Long id, ProductRequestDto requestDto) {
        Product updated = Product.builder()
                .id(id)
                .name(new Name(requestDto.name()))
                .price(requestDto.price())
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

//    private void validateProductName(String name) {
//        if (name.contains("카카오")) {
//            throw new IllegalArgumentException("“카카오”가 포함된 상품 이름은 MD 승인 후 사용 가능합니다.");
//        }
//    }
}