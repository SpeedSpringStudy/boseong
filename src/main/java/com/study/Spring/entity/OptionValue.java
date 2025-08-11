package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option_values")
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private OptionGroup optionGroup;

    @Column(name = "option_value", nullable = false, length = 50)
    private String optionValue;
}