package com.example.gamesApi.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "games")
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String[] genres;

    private String[] platforms;

    private BigDecimal price;

    private int ageGroup;

    private String urlImage;

    private String urlBanner;

    private String addedTime;

    private String releaseDate;

    private String synopsis;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        boolean validation = true;

        for(int i=0; i <= genres.length-1; i++){
            if(genres[i].equalsIgnoreCase("Action")
                    || genres[i].equalsIgnoreCase("Action-Adventure")
                    || genres[i].equalsIgnoreCase("Adventure")
                    || genres[i].equalsIgnoreCase("Card Game")
                    || genres[i].equalsIgnoreCase("Casual")
                    || genres[i].equalsIgnoreCase("City Builder")
                    || genres[i].equalsIgnoreCase("Comedy")
                    || genres[i].equalsIgnoreCase("Dungeon Crawler")
                    || genres[i].equalsIgnoreCase("Exploration")
                    || genres[i].equalsIgnoreCase("Fantasy")
                    || genres[i].equalsIgnoreCase("Fighting")
                    || genres[i].equalsIgnoreCase("First Person")
                    || genres[i].equalsIgnoreCase("Horror")
                    || genres[i].equalsIgnoreCase("Indie")
                    || genres[i].equalsIgnoreCase("Moba")
                    || genres[i].equalsIgnoreCase("Music")
                    || genres[i].equalsIgnoreCase("Narration")
                    || genres[i].equalsIgnoreCase("Open World")
                    || genres[i].equalsIgnoreCase("Party")
                    || genres[i].equalsIgnoreCase("Platformer")
                    || genres[i].equalsIgnoreCase("Puzzle")
                    || genres[i].equalsIgnoreCase("Racing")
                    || genres[i].equalsIgnoreCase("Retro")
                    || genres[i].equalsIgnoreCase("Rogue-Lite")
                    || genres[i].equalsIgnoreCase("RTS")
                    || genres[i].equalsIgnoreCase("Shooter")
                    || genres[i].equalsIgnoreCase("Space")
                    || genres[i].equalsIgnoreCase("Stealth")
                    || genres[i].equalsIgnoreCase("Survival")
                    || genres[i].equalsIgnoreCase("Tower Defense")
                    || genres[i].equalsIgnoreCase("Trivia")
                    || genres[i].equalsIgnoreCase("Turn-Based")
                    || genres[i].equalsIgnoreCase("Turn-Based Strategy")
                    || genres[i].equalsIgnoreCase("RPG")
                    || genres[i].equalsIgnoreCase("Simulation")
                    || genres[i].equalsIgnoreCase("Strategy")
                    || genres[i].equalsIgnoreCase("Sports")
                    || genres[i].equalsIgnoreCase("Battle Royale")){

            } else {
                validation = false;
            }
        }

        if(validation){
            this.genres = genres;
        } else {
            this.genres = null;
        }
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        boolean validation = true;

        for(int i=0; i <= platforms.length-1; i++){
            if(platforms[i].equalsIgnoreCase("PS5")
                    || platforms[i].equalsIgnoreCase("PS4")
                    || platforms[i].equalsIgnoreCase("Xbox 360")
                    || platforms[i].equalsIgnoreCase("Xbox One")
                    || platforms[i].equalsIgnoreCase("PC")
                    || platforms[i].equalsIgnoreCase("Nintendo Switch")
                    || platforms[i].equalsIgnoreCase("Android")
                    || platforms[i].equalsIgnoreCase("iOS")){

            } else {
                validation = false;
            }
        }

        if(validation){
            this.platforms = platforms;
        } else {
            this.platforms = null;
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

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
}
