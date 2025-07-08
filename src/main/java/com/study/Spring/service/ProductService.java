package com.study.Spring.service;

import com.study.Spring.entity.Product;
import com.study.Spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Long create(Product product) {
        return productRepository.save(product);
    }

    public Long update(Long id, Product product) {
        return productRepository.update(id, product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}