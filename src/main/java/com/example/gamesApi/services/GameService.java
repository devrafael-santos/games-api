package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDto;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import com.example.gamesApi.validates.ValidateGenres;
import com.example.gamesApi.validates.ValidatePlatforms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    final Calendar calendar = Calendar.getInstance();

    public GameModel getGame(UUID id){

        Optional<GameModel> game = gameRepository.findById(id);

        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found.");
        }

        return game.get();
    }


    public List<GameModel> getAllGames(){
        return gameRepository.findAll();
    }

    public GameModel createGame(GameDto gameDto) {

        String[] genres = gameDto.getGenres();
        String[] platforms = gameDto.getPlatforms();

        if(gameRepository.existsByTitle(gameDto.getTitle())){
            throw new GameAlreadyExistsException("Game already exists.");
        }

        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);

        GameModel gameModel = new GameModel(gameDto);

        gameModel.setAddedTime(dateFormat.format(calendar.getTime()));

        return gameRepository.save(gameModel);
    }


    public GameModel updateGame(UUID id, GameDto gameDto){

        Optional<GameModel> game = gameRepository.findById(id);

        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found.");
        }

        String[] genres = gameDto.getGenres();
        String[] platforms = gameDto.getPlatforms();
        String addedTime = game.get().getAddedTime();


        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);


        GameModel gameModel = new GameModel(gameDto);
        gameModel.setAddedTime(addedTime);

        gameRepository.delete(game.get());

        return gameRepository.save(gameModel);
    }

    public void deleteGame(UUID id){
        Optional<GameModel> game = gameRepository.findById(id);

        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found.");
        }

        gameRepository.delete(game.get());
    }


}
