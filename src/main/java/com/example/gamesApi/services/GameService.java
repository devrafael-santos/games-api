package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.exception.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameRecordDto getOneGame(UUID id){
        GameModel game = gameRepository.findById(id).get();
        if(game != null){
            return new GameRecordDto(game);
        }
        return null;
    }

    public List<GameModel> getAllGame(){
        return gameRepository.findAll();
    }

    public GameRecordDto createGame(GameModel gameModel) {
        
        Boolean gameExists = gameRepository.existsByGenres(gameModel.getGenres());
        Boolean plataformExists = gameRepository.existsByPlatforms(gameModel.getPlatforms());

        if(!gameExists && !plataformExists){
            GameModel game = new GameModel();
            game.setTitle(gameModel.getTitle());
            game.setGenres(gameModel.getGenres());
            game.setPlatforms(gameModel.getPlatforms());
            game.setAgeGroup(gameModel.getAgeGroup());
            game.setUrlImage(gameModel.getUrlImage());
            game.setUrlBanner(gameModel.getUrlBanner());
            game.setReleaseDate(gameModel.getReleaseDate());
            game.setSynopsis(gameModel.getSynopsis());
            game.setAddedTime(OffsetDateTime.now());
            game.setBuyGame(gameModel.getBuyGame());
            GameModel gameSaved = gameRepository.save(game);
            return new GameRecordDto(gameSaved);
        } else {
            throw new RuntimeException("Genres or Platforms já existentes");
        }
    }

    public GameRecordDto updateGame(UUID id, GameModel gameModel){

        if (id == null || gameModel == null) {
            throw new IllegalArgumentException("ID ou dados do jogo não podem ser nulos.");
        }

        GameModel gameExisting = gameRepository.findById(id).orElse(null);
        if (gameExisting == null) {
            throw new ResourceNotFoundException("Jogo não encontrado com o ID fornecido.");
        }

        gameExisting.setTitle(gameModel.getTitle());
        gameExisting.setGenres(gameModel.getGenres());
        gameExisting.setPlatforms(gameModel.getPlatforms());
        gameExisting.setAgeGroup(gameModel.getAgeGroup());
        gameExisting.setUrlImage(gameModel.getUrlImage());
        gameExisting.setUrlBanner(gameModel.getUrlBanner());
        gameExisting.setReleaseDate(gameModel.getReleaseDate());
        gameExisting.setSynopsis(gameModel.getSynopsis());
        gameExisting.setAddedTime(OffsetDateTime.now());
        gameExisting.setBuyGame(gameModel.getBuyGame());
        GameModel gameUpdated = gameRepository.save(gameExisting);

        return new GameRecordDto(gameUpdated);
    }

    public void deleteGame(UUID id){
        if(id == null){
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        GameModel game = gameRepository.findById(id).orElse(null);
        if(game == null){
            throw new ResourceNotFoundException("Jogo não encontrado com o ID fornecido.");
        } else {
            gameRepository.deleteById(id);
        }
    }

}
