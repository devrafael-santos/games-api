package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDto;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    GameService gameService;

    @MockBean
    GameRepository gameRepository;

    @Test
    @DisplayName("Should get a game successfully when the ID is valid")
    void getGameCase1() {
        UUID id = UUID.randomUUID();

        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto(id, "Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

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
    @DisplayName("Should create a Game successfully")
    void createGameCase1() {
        UUID id = UUID.randomUUID();

        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto(id, "Roblox", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);
        GameModel result = new GameModel(gameDto);


        Mockito.when(gameRepository.existsByTitle(gameDto.getTitle())).thenReturn(false);

        when(this.gameRepository.save(gameModel)).thenReturn(gameModel);

        when(this.gameService.createGame(gameDto)).thenReturn(result);


        assertThat(result).isEqualTo(gameModel);
    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Genre is invalid")
    void createGameCase2() {
        UUID id = UUID.randomUUID();
        String[] genres = {"Advent"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto(id, "Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));

    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Platform is invalid")
    void createGameCase3() {
        UUID id = UUID.randomUUID();
        String[] genres = {"Adventure"};
        String[] platforms = {"P"};

        GameDto gameDto = new GameDto(id, "Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should throw and Exception and not create a game when game name already exists")
    void createGameCase4() {
        UUID id = UUID.randomUUID();
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto(id, "Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Mockito.when(gameRepository.existsByTitle(gameDto.getTitle())).thenReturn(true);

        Assertions.assertThrows(GameAlreadyExistsException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should update a new game successfully")
    void updateGameCase1() {
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game is not found")
    void updateGameCase2() {
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Genre are invalid")
    void updateGameCase4() {
        UUID id = UUID.randomUUID();

        String[] validGenres = {"Adventure"};
        String[] invalidGenres = {"Advent"};
        String[] platforms = {"PC"};

        GameDto gameDto = new GameDto(id,"Minecraft", validGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);
        GameDto InvalidGameDto = new GameDto(id,"Minecraft", invalidGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(this.gameRepository.save(gameModel)).thenReturn(gameModel);

        when(this.gameRepository.existsById(gameModel.getId())).thenReturn(false);

        when(this.gameRepository.findById(id)).thenReturn(Optional.of(gameModel));


        Assertions.assertThrows(IllegalArgumentException.class, () -> this.gameService.updateGame(gameModel.getId(), InvalidGameDto));
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Platforms are invalid")
    void updateGameCase5() {
        UUID id = UUID.randomUUID();

        String[] genres = {"Adventure"};
        String[] validPlatforms = {"PC"};
        String[] invalidPlatforms = {"P"};

        GameDto gameDto = new GameDto(id,"Minecraft", genres, validPlatforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);
        GameDto InvalidGameDto = new GameDto(id,"Minecraft", genres, invalidPlatforms, 12, "url", "2010", "Synopsis", "link for buy");


        when(this.gameRepository.save(gameModel)).thenReturn(gameModel);

        when(this.gameRepository.existsById(gameModel.getId())).thenReturn(false);

        when(this.gameRepository.findById(id)).thenReturn(Optional.of(gameModel));


        Assertions.assertThrows(IllegalArgumentException.class, () -> this.gameService.updateGame(gameModel.getId(), InvalidGameDto));
    }

    @Test
    void deleteGame() {
    }

}