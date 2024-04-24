package com.example.gamesApi.models;

import com.example.gamesApi.dto.GameRecordDto;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

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

    private String urlBanner;

    private String addedTime;

    private String releaseDate;

    private String synopsis;

    private String buyGame;


    public GameModel(GameRecordDto gameDto) {BeanUtils.copyProperties(gameDto, this);}

    public GameModel() {}

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String[] getGenres() {return genres;}

    public void setGenres(String[] genres) {this.genres = genres;}

    public String[] getPlatforms() {return platforms;}

    public void setPlatforms(String[] platforms) {this.platforms = platforms;}

    public int getAgeGroup() {return ageGroup;}

    public void setAgeGroup(int ageGroup) {this.ageGroup = ageGroup;}

    public String getUrlImage() {return urlImage;}

    public void setUrlImage(String urlImage) {this.urlImage = urlImage;}

    public String getUrlBanner() {return urlBanner;}

    public void setUrlBanner(String urlBanner) {this.urlBanner = urlBanner;}

    public String getAddedTime() {return addedTime;}

    public void setAddedTime(String addedTime) {this.addedTime = addedTime;}

    public String getReleaseDate() {return releaseDate;}

    public void setReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}

    public String getSynopsis() {return synopsis;}

    public void setSynopsis(String synopsis) {this.synopsis = synopsis;}

    public String getBuyGame() {return buyGame;}

    public void setBuyGame(String buyGame) {this.buyGame = buyGame;}
}
