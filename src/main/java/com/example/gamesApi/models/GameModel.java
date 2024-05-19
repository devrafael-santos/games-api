package com.example.gamesApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "games")
@Data
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "The game title must be filled in.")
    private String title;

    private String[] genres;

    private String[] platforms;

    @NotNull
    private int ageGroup;

    @NotBlank(message = "The game image URL must be filled in.")
    private String urlImage;

    @NotBlank(message = "The game's release date must be filled in.")
    private String releaseDate;

    @NotBlank(message = "The game synopsis must be completed.")
    private String synopsis;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private OffsetDateTime addedTime;

    @NotBlank(message = "The game purchase link must be completed.")
    private String buyGame;

}
