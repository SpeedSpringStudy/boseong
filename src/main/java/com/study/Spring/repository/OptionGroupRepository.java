package com.study.Spring.repository;

import com.study.Spring.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {
    List<OptionGroup> findByProductId(Long productId);
}