package com.example.gamesApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record GameRecordDto(@NotBlank String title, @NotBlank String gender, @NotEmpty String[] platforms, @NotNull
                            BigDecimal price, @NotNull int ageGroup) {
}
