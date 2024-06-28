package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.IGameRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private IGameRepository IGameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    @DisplayName("Should get a game successfully when the ID is valid")
    void getGameCase1() {
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

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
        IGameRepository.save(new GameModel(gameDto));
        GameModel gameModel = new GameModel(gameDto);

        List<GameModel> listForTest = List.of(gameModel);
        when(IGameRepository.findAll()).thenReturn(listForTest);

        List<GameModel> result = gameService.getAllGames();

        assertThat(listForTest == result).isTrue();
    }

    @Test
    @DisplayName("Should create a Game successfully")
    void createGameCase1() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Calendar calendar = Calendar.getInstance();

        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Roblox", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        gameDto.setAddedTime(dateFormat.format(calendar.getTime()));
        GameModel gameModel = new GameModel(gameDto);

        when(IGameRepository.save(gameModel)).thenReturn(gameModel);

        GameModel result = gameService.createGame(gameDto);

        assertEquals(gameModel, result);
    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Genres is invalid")
    void createGameCase2() {
        String[] genres = {"Advent"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(gameDto));

    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Platforms is invalid")
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

        when(IGameRepository.existsByTitle(gameDto.getTitle())).thenReturn(true);

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

        when(IGameRepository.findById(gameModel.getId())).thenReturn(optionalGameModel);

        when(IGameRepository.save(newGameModel)).thenReturn(newGameModel);

        GameModel result = gameService.updateGame(gameModel.getId(), newGameDTO);

        assertThat(result == newGameModel).isTrue();
    }

    @Test
    @DisplayName("Should throw an Exception and not update a game when the game is not found")
    void updateGameCase2() {
        String[] oldGenres = {"Adventure"};
        String[] oldPlatforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", oldGenres, oldPlatforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(IGameRepository.findById(gameDto.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> gameService.updateGame(gameDto.getId(), gameDto));
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Genres are invalid")
    void updateGameCase4() {
        String[] validGenres = {"Adventure"};
        String[] invalidGenres = {"Advent"};
        String[] platforms = {"PC"};

        GameDTO gameDto = new GameDTO("Minecraft", validGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        GameDTO invalidGameDTO = new GameDTO("Minecraft", invalidGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.updateGame(gameModel.getId(), invalidGameDTO));
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

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.updateGame(gameModel.getId(), invalidGameDTO));
    }

    @Test
    @DisplayName("Should delete a game successfully")
    void deleteGameCase1() {
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};
        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        IGameRepository.delete(gameModel);

        verify(IGameRepository, atLeastOnce()).delete(gameModel);
    }

    @Test
    @DisplayName("Should throw and Exception when game not exists")
    void deleteGameCase2() {
        String[] genres = {"Adventure"};
        String[] platforms = {"PC"};
        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> gameService.deleteGame(gameModel.getId()));

    }

}