package com.study.Spring.service;

import com.study.Spring.entity.*;
import com.study.Spring.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionGroupRepository groupRepository;
    private final OptionValueRepository valueRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createOptionGroup(Long productId, String name) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        OptionGroup group = OptionGroup.builder()
                .product(product)
                .name(name)
                .build();
        return groupRepository.save(group).getTypeId();
    }

    @Transactional
    public Long createOptionValue(Long groupId, String value) {
        OptionGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹을 찾을 수 없습니다."));
        OptionValue optionValue = OptionValue.builder()
                .optionGroup(group)
                .value(value)
                .build();
        return valueRepository.save(optionValue).getValueId();
    }

    @Transactional(readOnly = true)
    public List<OptionGroup> getOptionGroups(Long productId) {
        return groupRepository.findByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<OptionValue> getOptionValues(Long groupId) {
        return valueRepository.findByOptionGroupId(groupId);
    }
}