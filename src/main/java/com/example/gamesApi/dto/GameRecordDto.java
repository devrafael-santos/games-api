package com.example.gamesApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record GameRecordDto(@NotBlank String title, @NotEmpty String[] genres, @NotEmpty String[] platforms, @NotNull BigDecimal price,
                            @NotNull int ageGroup, @NotBlank String urlImage, @NotBlank String urlBanner, @NotBlank String releaseDate, @NotBlank String synopsis) {
}
