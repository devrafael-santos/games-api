package com.example.gamesApi.repositories;

import com.example.gamesApi.models.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameModel, UUID> {

    boolean existsByGenres(String[] genres);
    boolean existsByPlatforms(String[] platforms);
}
