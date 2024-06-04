package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameRecordDto;
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

    public GameModel createGame(GameRecordDto gameRecordDto) {

        String[] genres = gameRecordDto.getGenres();
        String[] platforms = gameRecordDto.getPlatforms();

        if(gameRepository.existsByTitle(gameRecordDto.getTitle())){
            throw new GameAlreadyExistsException("Game already exists.");
        }


        //verifying the genres attribute
        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);

        GameModel gameModel = new GameModel(gameRecordDto);

        gameModel.setAddedTime(dateFormat.format(calendar.getTime()));

        return gameRepository.save(gameModel);
    }


    public GameModel updateGame(UUID id, GameRecordDto gameRecordDto){

        Optional<GameModel> game = gameRepository.findById(id);

        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found.");
        }

        String[] genres = gameRecordDto.getGenres();
        String[] platforms = gameRecordDto.getPlatforms();
        String addedTime = game.get().getAddedTime();


        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);


        GameModel gameModel = new GameModel(gameRecordDto);
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
