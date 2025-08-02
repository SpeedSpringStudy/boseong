package com.study.Spring.repository;

import com.study.Spring.entity.Stock;
import com.study.Spring.entity.ProductComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductComposition(ProductComposition productComposition);
}