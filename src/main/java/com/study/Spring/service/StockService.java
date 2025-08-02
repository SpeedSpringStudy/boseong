package com.study.Spring.service;

import com.study.Spring.entity.*;
import com.study.Spring.repository.*;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductCompositionRepository compositionRepository;

    @Transactional
    public void createStock(Long combinationId, int quantity) {
        ProductComposition composition = compositionRepository.findById(combinationId)
                .orElseThrow(() -> new IllegalArgumentException("상품 조합을 찾을 수 없습니다."));
        Stock stock = Stock.builder()
                .productComposition(composition)
                .quantity(quantity)
                .build();
        stockRepository.save(stock);
    }

    @Transactional
    public void decreaseStock(Long combinationId, int count) {
        Stock stock = stockRepository.findByProductComposition(
                compositionRepository.findById(combinationId)
                        .orElseThrow(() -> new IllegalArgumentException("상품 조합을 찾을 수 없습니다."))
        ).orElseThrow(() -> new IllegalArgumentException("재고를 찾을 수 없습니다."));

        if (stock.getQuantity() < count) {
            throw new IllegalArgumentException("재고 부족");
        }

        stock.setQuantity(stock.getQuantity() - count);
        stockRepository.save(stock);
    }

    // 재시도 로직 (낙관적 락 충돌 시)
    public void decreaseStockWithRetry(Long combinationId, int count) {
        int retries = 3;
        while (retries > 0) {
            try {
                decreaseStock(combinationId, count);
                return;
            } catch (OptimisticLockException e) {
                retries--;
                if (retries == 0) throw new RuntimeException("재고 감소 실패 (동시성 충돌)");
            }
        }
    }
}