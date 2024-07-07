package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.GameNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.IGameRepository;
import com.example.gamesApi.validates.ValidateGenres;
import com.example.gamesApi.validates.ValidatePlatforms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GameService {

    @Autowired
    IGameRepository iGameRepository;

    public GameModel getGame(UUID id){

        Optional<GameModel> game = iGameRepository.findById(id);

        if(game.isEmpty()){
            throw new GameNotFoundException();
        }

        return game.get();
    }


    public List<GameModel> getAllGames(){
        return iGameRepository.findAll();
    }

    public List<GameModel> searchGames(String title){
        return iGameRepository.findByTitleIgnoreCaseContaining(title, Sort.by(Sort.Direction.ASC, "title"));
    }

    public List<GameModel> searchByGenre(String genre){

        return iGameRepository.findByGenresIn(genre.toUpperCase());
    }

    public GameModel createGame(GameDTO gameDto) {

        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        final Calendar calendar = Calendar.getInstance();

        List<String> genres = gameDto.getGenres();
        List<String> platforms = gameDto.getPlatforms();

        if(iGameRepository.existsByTitle(gameDto.getTitle())){
            throw new GameAlreadyExistsException(gameDto.getTitle());
        }

        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);

        GameModel gameModel = new GameModel(gameDto);

        gameModel.setAddedTime(dateFormat.format(calendar.getTime()));

        return iGameRepository.save(gameModel);
    }


    public GameModel updateGame(UUID id, GameDTO gameDto){

        Optional<GameModel> game = iGameRepository.findById(id);

        if(game.isEmpty()){
            throw new GameNotFoundException();
        }

        List<String> genres = gameDto.getGenres();
        List<String> platforms = gameDto.getPlatforms();
        String addedTime = game.get().getAddedTime();


        new ValidateGenres(genres);
        new ValidatePlatforms(platforms);


        GameModel gameModel = new GameModel(gameDto);
        gameModel.setAddedTime(addedTime);

        iGameRepository.delete(game.get());

        return iGameRepository.save(gameModel);
    }

    public void deleteGame(UUID id){
        Optional<GameModel> game = iGameRepository.findById(id);

        if(game.isEmpty()){
            throw new GameNotFoundException();
        }

        iGameRepository.delete(game.get());
    }


}
