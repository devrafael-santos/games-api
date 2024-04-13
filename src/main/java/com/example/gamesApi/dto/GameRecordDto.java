package com.example.gamesApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record GameRecordDto(@NotBlank String title, @NotBlank String gender, @NotNull String[] platforms, @NotNull
                            BigDecimal price, @NotNull int ageGroup) {
}
