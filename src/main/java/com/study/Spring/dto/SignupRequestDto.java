package com.study.Spring.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
        @NotBlank String username,
        @NotBlank String password
) {}