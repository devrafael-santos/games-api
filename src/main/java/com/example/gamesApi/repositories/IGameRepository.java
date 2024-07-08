package com.example.gamesApi.repositories;

import com.example.gamesApi.models.GameModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IGameRepository extends JpaRepository<GameModel, UUID> {

    boolean existsByTitle(String title);

    List<GameModel> findByTitleIgnoreCaseContaining(String title, Sort sort);


    @Query("SELECT g FROM GameModel g WHERE UPPER(:genre) MEMBER OF g.genres")
    List<GameModel> findByGenresIn(String genre);

    @Query("SELECT g FROM GameModel g WHERE UPPER(:platform) MEMBER OF g.platforms")
    List<GameModel> findByPlatformIn(String platform);

}
