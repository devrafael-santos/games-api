package com.example.gamesApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "games")
public class GameModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
