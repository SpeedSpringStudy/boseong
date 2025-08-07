package com.study.Spring.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
        @NotBlank String email,
        @NotBlank String password
) {}