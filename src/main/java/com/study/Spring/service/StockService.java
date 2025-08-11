package com.study.Spring.service;

import com.study.Spring.dto.StockRequest;
import com.study.Spring.dto.StockResponse;
import com.study.Spring.entity.ProductComposition;
import com.study.Spring.entity.Stock;
import com.study.Spring.repository.ProductCompositionRepository;
import com.study.Spring.repository.StockRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductCompositionRepository productCompositionRepository;

    private static final int MAX_RETRIES = 3; // 재시도 최대 횟수

    @Transactional
    public StockResponse createStock(StockRequest request) {
        ProductComposition composition = productCompositionRepository.findById(request.getCompositionId())
                .orElseThrow(() -> new IllegalArgumentException("조합을 찾을 수 없습니다."));

        Stock stock = Stock.builder()
                .composition(composition)
                .quantity(request.getQuantity())
                .build();

        Stock savedStock = stockRepository.save(stock);
        return new StockResponse(savedStock.getId(), composition.getId(), savedStock.getQuantity());
    }

    @Transactional(readOnly = true)
    public StockResponse getStock(Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("재고를 찾을 수 없습니다."));
        return new StockResponse(stock.getId(), stock.getComposition().getId(), stock.getQuantity());
    }

    @Transactional
    public StockResponse updateStock(Long stockId, StockRequest request) {
        int attempt = 0;
        while (true) {
            try {
                attempt++;
                Stock stock = stockRepository.findById(stockId)
                        .orElseThrow(() -> new IllegalArgumentException("재고를 찾을 수 없습니다."));
                stock.setQuantity(request.getQuantity());
                Stock updated = stockRepository.saveAndFlush(stock);
                return new StockResponse(updated.getId(), updated.getComposition().getId(), updated.getQuantity());
            } catch (OptimisticLockException e) {
                if (attempt >= MAX_RETRIES) {
                    throw new RuntimeException("재고 업데이트 충돌이 너무 많이 발생했습니다. 다시 시도해주세요.");
                }
            }
        }
    }
}