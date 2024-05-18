package com.example.gamesApi.dto;

import com.example.gamesApi.models.GameModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class GameRecordDto {
    private UUID id;
    
    @NotBlank(message = "O título do jogo deve ser preenchido.")
    private String title;
    
    private String[] genres;
    
    private String[] platforms;

    @NotNull
    private int ageGroup;

    @NotBlank(message = "A URL da imagem do jogo deve ser preenchida.")
    private String urlImage;

    @NotBlank(message = "A URL do banner do jogo deve ser preenchida.")
    private String urlBanner;

    @NotBlank(message = "A data de lançamento do jogo deve ser preenchida.")
    private String releaseDate;

    @NotBlank(message = "A sinopse do jogo deve ser preenchida.")
    private String synopsis;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private OffsetDateTime addedTime;

    @NotBlank(message = "O link de compra do jogo deve ser preenchido.")
    private String buyGame;

    public GameRecordDto(GameModel game) {
        BeanUtils.copyProperties(game, this);
    }

}
