package com.example.gamesApi.models;

import com.example.gamesApi.dto.GameDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "games")
public class GameModel extends RepresentationModel<GameModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @ElementCollection
    private Set<String> genres;

    @ElementCollection
    private Set<String> platforms;

    private int ageGroup;

    private String urlImage;

    private String addedTime;

    private String releaseDate;

    private String synopsis;

    private String buyGame;


    public GameModel(GameDTO gameDto) {BeanUtils.copyProperties(gameDto, this);}
    public GameModel() {}

}
