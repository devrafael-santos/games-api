package com.example.gamesApi.repositories;

import com.example.gamesApi.models.GameModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IGameRepository extends JpaRepository<GameModel, UUID> {

    boolean existsByTitle(String title);

    List<GameModel> findByTitleIgnoreCaseContaining(String title, Sort sort);

}
