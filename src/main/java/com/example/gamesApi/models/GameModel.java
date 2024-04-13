package com.example.gamesApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

@Entity
@Table(name = "games")
public class GameModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String title;


    private String gender;

    private String[] platforms;


    private BigDecimal price;


    private int ageGroup;



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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
