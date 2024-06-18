package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDto;
import com.example.gamesApi.exceptions.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    @DisplayName("Should get a game successfully when the ID is valid")
    void getGameCase1() {
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "13/06/2024", "link for buy");

        GameModel gameModel = new GameModel(gameDto);

        Mockito.when(gameRepository.save(gameModel)).thenReturn(new GameModel());


        Mockito.when(gameRepository.findById(gameModel.getId()));

    }

    @Test
    @DisplayName("Should throw an Exception when the ID is invalid")
    void getGameCase2() {
        UUID id = UUID.randomUUID();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.gameService.getGame(id));
    }

    @Test
    void getAllGames() {
    }

    @Test
    @DisplayName("Should create a Game successfully when the Platforms and Genres are valid")
    void createGameCase1() {
        
    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Genre is invalid")
    void createGameCase2() {

        String[] genres = {"Advent"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "13/06/2024", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));

    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Platform is invalid")
    void createGameCase3() {

        String[] genres = {"Adventure"};
        String[] platforms = {"P"};

        GameDto gameDto = new GameDto("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "13/06/2024", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should throw and Exception and not create a game when game name already exists")
    void createGameCase4() {

        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "13/06/2024", "link for buy");

        Mockito.when(gameRepository.existsByTitle(gameDto.getTitle())).thenReturn(true);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> gameService.createGame(gameDto));

    }

    @Test
    void updateGame() {
    }

    @Test
    void deleteGame() {
    }

}