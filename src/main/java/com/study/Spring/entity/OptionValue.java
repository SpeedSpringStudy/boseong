package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option_values")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long valueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private OptionGroup optionGroup;

    @Column(nullable = false, length = 50)
    private String value;
}