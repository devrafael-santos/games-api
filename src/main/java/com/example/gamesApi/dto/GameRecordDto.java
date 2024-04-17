package com.example.gamesApi.dto;

import java.math.BigDecimal;

public record GameRecordDto(String title, String[] genres, String[] platforms, BigDecimal price, int ageGroup, String urlImage, String urlBanner, String releaseDate, String synopsis) {
}
