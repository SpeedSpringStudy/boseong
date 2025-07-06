package com.study.Spring.repository;

import com.study.Spring.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private long sequence = 0L;

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Product save(Product product) {
        product.setId(++sequence);
        store.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Product update(Long id, Product updated) {
        Product product = store.get(id);
        if (product != null) {
            product.setName(updated.getName());
            product.setPrice(updated.getPrice());
        }
        return product;
    }

    public void delete(Long id) {
        store.remove(id);
    }
}