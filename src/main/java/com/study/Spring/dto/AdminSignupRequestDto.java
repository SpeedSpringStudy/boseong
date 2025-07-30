package com.study.Spring.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminSignupRequestDto(
        @NotBlank String username,
        @NotBlank String password
) {}