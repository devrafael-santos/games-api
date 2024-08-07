package com.example.gamesApi.repositories;

import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.models.GameModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class IGameRepositoryTest {

    @Autowired
    IGameRepository IGameRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Game successfully from database")
    void existsByTitleCase1() {
        UUID id = UUID.randomUUID();
        Set<String> genres = Set.of("Adventure");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        this.createOneGame(gameDto);

        boolean result = this.IGameRepository.existsByTitle(gameDto.getTitle());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Should not get Game successfully from database when Game not exists")
    void existsByTitleCase2() {

        String title = "Minecraft";

        boolean result = this.IGameRepository.existsByTitle(title);

        assertThat(result).isFalse();
    }

    private void createOneGame(GameDTO gameDto) {
        GameModel gameModel = new GameModel(gameDto);
        this.entityManager.persist(gameModel);
    }
}