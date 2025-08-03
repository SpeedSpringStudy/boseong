package com.study.Spring.service;

import com.study.Spring.dto.StockRequest;
import com.study.Spring.dto.StockResponse;
import com.study.Spring.entity.Product;
import com.study.Spring.entity.ProductComposition;
import com.study.Spring.entity.Stock;
import com.study.Spring.repository.ProductCompositionRepository;
import com.study.Spring.repository.ProductRepository;
import com.study.Spring.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final ProductCompositionRepository productCompositionRepository;

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
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("재고를 찾을 수 없습니다."));
        stock.setQuantity(request.getQuantity());
        Stock updated = stockRepository.save(stock);
        return new StockResponse(updated.getId(), updated.getComposition().getId(), updated.getQuantity());
    }
}