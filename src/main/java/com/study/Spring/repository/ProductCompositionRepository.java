package com.study.Spring.repository;

import com.study.Spring.entity.ProductComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCompositionRepository extends JpaRepository<ProductComposition, Long> {
    List<ProductComposition> findByProductId(Long productId);
}