package com.study.Spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull Long optionId,
        @Min(1) Integer quantity,
        String message
) {}