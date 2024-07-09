package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.GameNotFoundException;
import com.example.gamesApi.exceptions.InvalidGenreException;
import com.example.gamesApi.exceptions.InvalidPlatformException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.IGameRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

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
    @DisplayName("Should throw an Exception when the ID is invalid")
    void getGameCase1() {
        UUID id = UUID.randomUUID();

        Assertions.assertThrows(GameNotFoundException.class, () -> this.gameService.getGame(id));
    }

    @Test
    @DisplayName("Should get a game successfully when the ID is valid")
    void getGameCase2() {
        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

        Optional<GameModel> result = Optional.of(this.gameService.getGame(gameModel.getId()));

        assertThat(result.get() == gameModel).isTrue();
    }

    @Test
    @DisplayName("Should get a list of Games ordered by title")
    void searchGame() {
        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto1 = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel1 = new GameModel(gameDto1);

        GameDTO gameDto2 = new GameDTO("Motor Sport", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel2 = new GameModel(gameDto2);

        List<GameModel> listOfGames = new ArrayList<>();
        listOfGames.add(gameModel1);
        listOfGames.add(gameModel2);

        when(IGameRepository.findByTitleIgnoreCaseContaining("m", Sort.by(Sort.Direction.ASC, "title"))).thenReturn(listOfGames);

        List<GameModel> result = gameService.searchGames("m");

        Assertions.assertEquals(gameModel1, result.get(0));
    }

    @Test
    @DisplayName("Should return all games saved")
    void getAllGames() {
        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        IGameRepository.save(new GameModel(gameDto));
        GameModel gameModel = new GameModel(gameDto);

        List<GameModel> listForTest = List.of(gameModel);
        when(IGameRepository.findAll()).thenReturn(listForTest);

        List<GameModel> result = gameService.getAllGames();

        assertThat(listForTest == result).isTrue();
    }


    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Genres is invalid")
    void createGameCase1() {
        Set<String> genres = Set.of("ADVENT");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(InvalidGenreException.class, () -> gameService.createGame(gameDto));

    }

    @Test
    @DisplayName("Should throw and Exception and not create a Game when some Platforms is invalid")
    void createGameCase2() {
        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("P");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        Assertions.assertThrows(InvalidPlatformException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should throw and Exception and not create a game when game name already exists")
    void createGameCase3() {
        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(IGameRepository.existsByTitle(gameDto.getTitle())).thenReturn(true);

        Assertions.assertThrows(GameAlreadyExistsException.class, () -> gameService.createGame(gameDto));
    }

    @Test
    @DisplayName("Should create a Game successfully")
    void createGameCase4() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Calendar calendar = Calendar.getInstance();

        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Roblox", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        gameDto.setAddedTime(dateFormat.format(calendar.getTime()));
        GameModel gameModel = new GameModel(gameDto);

        when(IGameRepository.save(gameModel)).thenReturn(gameModel);

        GameModel result = gameService.createGame(gameDto);

        assertEquals(gameModel, result);
    }

    @Test
    @DisplayName("Should throw an Exception and not update a game when the game is not found")
    void updateGameCase1() {
        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(IGameRepository.findById(gameDto.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(GameNotFoundException.class, () -> gameService.updateGame(gameDto.getId(), gameDto));
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Genres are invalid")
    void updateGameCase2() {
        Set<String> validGenres = Set.of("ADVENTURE");
        Set<String> invalidGenres = Set.of("ADVENT");
        Set<String> platforms = Set.of("PC");

        GameDTO validGameDto = new GameDTO("Minecraft", validGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(validGameDto);

        GameDTO invalidGameDTO = new GameDTO("Minecraft", invalidGenres, platforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

        Assertions.assertThrows(InvalidGenreException.class, () -> gameService.updateGame(gameModel.getId(), invalidGameDTO));
    }

    @Test
    @DisplayName("Should throw an Exception and not create a game when the game Platforms are invalid")
    void updateGameCase3() {

        Set<String>  genres = Set.of("ADVENTURE");
        Set<String> validPlatforms = Set.of("PC");
        Set<String> invalidPlatforms = Set.of("P");

        GameDTO validGameDto = new GameDTO("Minecraft", genres, validPlatforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(validGameDto);

        GameDTO invalidGameDTO = new GameDTO("Minecraft", genres, invalidPlatforms, 12, "url", "2010", "Synopsis", "link for buy");

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.of(gameModel));

        Assertions.assertThrows(InvalidPlatformException.class, () -> gameService.updateGame(gameModel.getId(), invalidGameDTO));
    }

    @Test
    @DisplayName("Should update a new game successfully")
    void updateGameCase4() {
        Set<String> oldGenres = Set.of("ADVENTURE");
        Set<String> newGenres = Set.of("ACTION");

        Set<String> oldPlatforms = Set.of("PC");
        Set<String> newPlatforms = Set.of("PC", "PS5");

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
    @DisplayName("Should throw and Exception when game not exists")
    void deleteGameCase1() {
        Set<String>  genres = Set.of("ADVENTURE");
        Set<String>  platforms = Set.of("PC");

        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        when(IGameRepository.findById(gameModel.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(GameNotFoundException.class, () -> gameService.deleteGame(gameModel.getId()));

    }

    @Test
    @DisplayName("Should delete a game successfully")
    void deleteGameCase2() {

        Set<String> genres = Set.of("ADVENTURE");
        Set<String> platforms = Set.of("PC");
        GameDTO gameDto = new GameDTO("Minecraft", genres, platforms, 12, "url", "2010", "Synopsis", "link for buy");
        GameModel gameModel = new GameModel(gameDto);

        IGameRepository.delete(gameModel);

        verify(IGameRepository, atLeastOnce()).delete(gameModel);
    }

}