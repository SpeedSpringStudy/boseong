package com.study.Spring.repository;

import com.study.Spring.entity.ProductOptionMapping;
import com.study.Spring.entity.ProductOptionMappingId;
import com.study.Spring.entity.ProductComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionMappingRepository extends JpaRepository<ProductOptionMapping, ProductOptionMappingId> {
    List<ProductOptionMapping> findByProductComposition(ProductComposition productComposition);
}