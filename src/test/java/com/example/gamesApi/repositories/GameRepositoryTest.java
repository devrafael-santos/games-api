package com.example.gamesApi.repositories;

import com.example.gamesApi.dto.GameDto;
import com.example.gamesApi.models.GameModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Game successfully from database")
    void existsByTitleSuccess() {

        String genres[] = {"Adventure"};
        String platforms[] = {"PC"};

        GameDto gameDto = new GameDto("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "13/06/2024", "link for buy");
        this.createGame(gameDto);

        boolean result = this.gameRepository.existsByTitle(gameDto.getTitle());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Should not get Game successfully from database when Game not exists")
    void existsByTitleFailed() {

        String title = "Minecraft";

        boolean result = this.gameRepository.existsByTitle(title);

        assertThat(result).isFalse();
    }

    private GameModel createGame(GameDto gameDto){
        GameModel gameModel = new GameModel(gameDto);
        this.entityManager.persist(gameModel);
        return gameModel;
    }
}