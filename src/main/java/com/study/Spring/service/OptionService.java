package com.study.Spring.service;

import com.study.Spring.dto.OptionGroupResponse;
import com.study.Spring.dto.OptionValueResponse;
import com.study.Spring.entity.*;
import com.study.Spring.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionGroupRepository groupRepository;
    private final OptionValueRepository valueRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OptionGroupResponse createOptionGroup(Long productId, String name) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        OptionGroup group = OptionGroup.builder()
                .product(product)
                .name(name)
                .build();
        OptionGroup saved = groupRepository.save(group);
        return new OptionGroupResponse(saved.getTypeId(), saved.getName(), saved.getProduct().getId());
    }

    @Transactional
    public OptionValueResponse createOptionValue(Long groupId, String value) {
        OptionGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("옵션 그룹을 찾을 수 없습니다."));
        OptionValue optionValue = OptionValue.builder()
                .optionGroup(group)
                .optionValue(value)
                .build();
        OptionValue saved = valueRepository.save(optionValue);
        return new OptionValueResponse(saved.getId(), saved.getOptionValue(), saved.getOptionGroup().getTypeId());
    }

    @Transactional(readOnly = true)
    public List<OptionGroupResponse> getOptionGroups(Long productId) {
        return groupRepository.findByProductId(productId)
                .stream()
                .map(group -> new OptionGroupResponse(group.getTypeId(), group.getName(), group.getProduct().getId()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OptionValueResponse> getOptionValues(Long groupId) {
        return valueRepository.findByOptionGroupTypeId(groupId)
                .stream()
                .map(value -> new OptionValueResponse(value.getId(), value.getOptionValue(), value.getOptionGroup().getTypeId()))
                .collect(Collectors.toList());
    }
}