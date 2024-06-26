package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.IGameRepository;
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
    IGameRepository IGameRepository;

    public GameModel getGame(UUID id){

        Optional<GameModel> game = IGameRepository.findById(id);

        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found.");
        }

        return game.get();
    }


    public List<GameModel> getAllGames(){
        return IGameRepository.findAll();
    }

    public GameModel createGame(GameDTO gameDto) {

        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Calendar calendar = Calendar.getInstance();

        String[] genres = gameDto.getGenres();
        String[] platforms = gameDto.getPlatforms();

        if(IGameRepository.existsByTitle(gameDto.getTitle())){
            throw new GameAlreadyExistsException(gameDto.getTitle());
        }

        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);

        GameModel gameModel = new GameModel(gameDto);

        gameModel.setAddedTime(dateFormat.format(calendar.getTime()));

        return IGameRepository.save(gameModel);
    }


    public GameModel updateGame(UUID id, GameDTO gameDto){

        Optional<GameModel> game = IGameRepository.findById(id);

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

        IGameRepository.delete(game.get());

        return IGameRepository.save(gameModel);
    }

    public void deleteGame(UUID id){
        Optional<GameModel> game = IGameRepository.findById(id);

        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found.");
        }

        IGameRepository.delete(game.get());
    }


}
