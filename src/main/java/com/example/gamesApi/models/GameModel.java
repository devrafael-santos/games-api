package com.example.gamesApi.models;

import com.example.gamesApi.dto.GameRecordDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "games")
public class GameModel extends RepresentationModel<GameModel> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String[] genres;

    private String[] platforms;

    private int ageGroup;

    private String urlImage;

    private String addedTime;

    private String releaseDate;

    private String synopsis;

    private String buyGame;


    public GameModel(GameRecordDto gameDto) {BeanUtils.copyProperties(gameDto, this);}

    public GameModel() {}

}
