package com.study.Spring.repository;

import com.study.Spring.entity.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionValueRepository extends JpaRepository<OptionValue, Long> {
    List<OptionValue> findByOptionGroupId(Long optionGroupId);
}