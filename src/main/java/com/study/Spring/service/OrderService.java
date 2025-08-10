package com.study.Spring.service;

import com.study.Spring.dto.OrderRequest;
import com.study.Spring.dto.OrderResponse;
import com.study.Spring.entity.*;
import com.study.Spring.repository.*;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductCompositionRepository compositionRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;
    private final KakaoMessageService kakaoMessageService;

    private static final int MAX_RETRY = 3;

    @Transactional
    public OrderResponse placeOrder(Long userId, OrderRequest req) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        ProductComposition composition = compositionRepository.findById(req.optionId()).orElseThrow(() -> new IllegalArgumentException("옵션을 찾을 수 없습니다."));
        Integer qty = req.quantity() == null ? 1 : req.quantity();
        if (qty < 1) throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");

        int attempt = 0;
        while (true) {
            try {
                attempt++;
                Stock stock = stockRepository.findByComposition(composition).orElseThrow(() -> new IllegalArgumentException("재고가 없습니다."));
                if (stock.getQuantity() < qty) throw new IllegalArgumentException("재고가 부족합니다.");
                stock.setQuantity(stock.getQuantity() - qty);
                stockRepository.saveAndFlush(stock);
                break;
            } catch (OptimisticLockException e) {
                if (attempt >= MAX_RETRY) throw new IllegalStateException("동시 주문 충돌이 발생했습니다. 다시 시도하세요.");
            }
        }

        Order order = Order.builder()
                .user(user)
                .composition(composition)
                .quantity(qty)
                .message(req.message())
                .orderDateTime(LocalDateTime.now())
                .build();
        Order saved = orderRepository.save(order);

        wishlistRepository.deleteByUserIdAndProductId(user.getId(), composition.getProduct().getId());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                kakaoMessageService.sendOrderMemo(user, saved);
            }
        });

        return new OrderResponse(saved.getId(), composition.getId(), saved.getQuantity(), saved.getOrderDateTime(), saved.getMessage());
    }
}