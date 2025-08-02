package com.study.Spring.service;

import com.study.Spring.entity.*;
import com.study.Spring.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCompositionService {

    private final ProductCompositionRepository compositionRepository;
    private final ProductRepository productRepository;
    private final ProductOptionMappingRepository mappingRepository;
    private final OptionValueRepository optionValueRepository;

    @Transactional
    public Long createComposition(Long productId, List<Long> optionValueIds) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // SKU 생성
        ProductComposition composition = ProductComposition.builder()
                .product(product)
                .isValid(ProductComposition.ValidStatus.VALID)
                .build();
        compositionRepository.save(composition);

        // 옵션 값 매핑
        for (Long valueId : optionValueIds) {
            OptionValue value = optionValueRepository.findById(valueId)
                    .orElseThrow(() -> new IllegalArgumentException("옵션 값을 찾을 수 없습니다."));
            ProductOptionMapping mapping = ProductOptionMapping.builder()
                    .productComposition(composition)
                    .optionValue(value)
                    .build();
            mappingRepository.save(mapping);
        }

        return composition.getCombinationId();
    }

    @Transactional(readOnly = true)
    public List<ProductComposition> getCompositionsByProduct(Long productId) {
        return compositionRepository.findByProductId(productId);
    }
}