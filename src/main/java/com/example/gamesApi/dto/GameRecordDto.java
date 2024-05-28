package com.example.gamesApi.dto;

import com.example.gamesApi.models.GameModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class GameRecordDto {
    private UUID id;

    @NotBlank
    private String title;

    private String[] genres;

    private String[] platforms;


    @NotNull
    private int ageGroup;

    @NotBlank
    private String urlImage;

    @NotBlank
    private String releaseDate;

    @NotBlank
    private String synopsis;

    private String addedTime;

    @NotBlank
    private String buyGame;

    public GameRecordDto() {
    }

    public GameRecordDto(GameModel game) {
        BeanUtils.copyProperties(game, this);
    }
}
