package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
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
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        when(gameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

        Optional<GameModel> result = Optional.of(this.gameService.getGame(gameModel.getId()));

        assertThat(result.get() == gameModel).isTrue();
    }

    @Test
    @DisplayName("Should throw an Exception when the ID is invalid")
    void getGameCase2() {
        UUID id = UUID.randomUUID();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> this.gameService.getGame(id));
    }

    @Test
    @DisplayName("Should return all games saved")
    void getAllGames() {
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        this.gameRepository.save(new GameModel(gameDto));

        List<GameModel> listForTest = this.gameRepository.findAll();

        when(gameService.getAllGames()).thenReturn(listForTest);
        List<GameModel> result = this.gameService.getAllGames();

        assertThat(listForTest == result).isTrue();
    }

    @Test
    @DisplayName("Should create a Game successfully")
    void createGameCase1() {
        UUID id = UUID.randomUUID();

        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Roblox", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);
        GameModel result = new GameModel(gameDto);


        when(gameRepository.existsByTitle(gameDto.getTitle())).thenReturn(false);

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

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));

    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Platform is invalid")
    void createGameCase3() {
        String[] genres = {"Adventure"};
        String[] platforms = {"P"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should throw and Exception and not create a game when game name already exists")
    void createGameCase4() {
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(gameRepository.existsByTitle(gameDto.getTitle())).thenReturn(true);

        Assertions.assertThrows(GameAlreadyExistsException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should update a new game successfully")
    void updateGameCase1() {
        String[] oldGenres = {"Adventure"};
        String[] newGenres = {"Action"};

        String[] oldPlatforms = {"PC"};
        String[] newPlatforms = {"PC", "PS5"};

        GameDTO oldGameDto = new GameDTO("Minecraft", oldGenres, oldPlatforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(oldGameDto);

        GameDTO newGameDTO = new GameDTO("Minecraft", newGenres, newPlatforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel newGameModel = new GameModel(newGameDTO);

        Optional<GameModel> optionalGameModel = Optional.of(gameModel);

        when(gameRepository.findById(gameModel.getId())).thenReturn(optionalGameModel);
        gameRepository.delete(optionalGameModel.get());

        when(gameRepository.save(newGameModel)).thenReturn(newGameModel);

        GameModel result = this.gameService.updateGame(gameModel.getId(), newGameDTO);

        assertThat(result == newGameModel).isTrue();
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game is not found")
    void updateGameCase2() {
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Genre are invalid")
    void updateGameCase4() {
        String[] validGenres = {"Adventure"};
        String[] invalidGenres = {"Advent"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", validGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);
        GameDTO invalidGameDTO = new GameDTO("Minecraft", invalidGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(this.gameRepository.save(gameModel)).thenReturn(gameModel);

        when(this.gameRepository.existsById(gameModel.getId())).thenReturn(false);

        when(this.gameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));


        Assertions.assertThrows(IllegalArgumentException.class, () -> this.gameService.updateGame(gameModel.getId(), invalidGameDTO));
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Platforms are invalid")
    void updateGameCase5() {
        String[] genres = {"Adventure"};
        String[] validPlatforms = {"PC"};
        String[] invalidPlatforms = {"P"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, validPlatforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);
        GameDTO invalidGameDTO = new GameDTO("Minecraft", genres, invalidPlatforms, 12, "url", "2010", "Synopsis", "link for buy");


        when(this.gameRepository.save(gameModel)).thenReturn(gameModel);

        when(this.gameRepository.existsById(gameModel.getId())).thenReturn(false);

        when(this.gameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));


        Assertions.assertThrows(IllegalArgumentException.class, () -> this.gameService.updateGame(gameModel.getId(), invalidGameDTO));
    }

    @Test
    @DisplayName("Should delete a game successfully")
    void deleteGameCase1() {
    }

    @Test
    @DisplayName("Should throw and Exception when game not exists")
    void deleteGameCase2(){

    }

}