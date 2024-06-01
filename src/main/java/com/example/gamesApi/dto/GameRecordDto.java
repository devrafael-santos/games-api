package com.example.gamesApi.dto;

import com.example.gamesApi.models.GameModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;

import java.io.Serializable;
import java.util.UUID;

@Data
public class GameRecordDto implements Serializable {
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

    public void add(Link gameList) {

    }
}
