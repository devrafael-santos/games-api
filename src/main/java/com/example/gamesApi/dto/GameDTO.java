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
public class GameDTO implements Serializable {
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

    public GameDTO() {
    }

    public GameDTO(GameModel game) {
        BeanUtils.copyProperties(game, this);
    }

    public GameDTO(String title, String[] genres, String[] platforms, int ageGroup, String urlImage, String releaseDate, String synopsis, String buyGame) {
        this.title = title;
        this.genres = genres;
        this.platforms = platforms;
        this.ageGroup = ageGroup;
        this.urlImage = urlImage;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.buyGame = buyGame;
    }

    public void add(Link gameList) {

    }
}
