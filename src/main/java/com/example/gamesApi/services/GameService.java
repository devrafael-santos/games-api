package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.entities.Game;
import com.example.gamesApi.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {


    @Autowired
    GameRepository gameRepository;
    final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");

    final Calendar calendar = Calendar.getInstance();

    @Transactional
    public Object createGame(GameRecordDto gameRecordDto){
        if(gameRecordDto == null){
            return "Enter a valid request body";
        }

        var gameModel = new Game();
        BeanUtils.copyProperties(gameRecordDto, gameModel);

        if(gameModel.getTitle().isEmpty()){
            return "Enter a valid Title.";
        }
        if(gameModel.getUrlImage().isEmpty()){
            return "Enter a valid URL for the game image.";
        }
        if(gameModel.getUrlBanner().isEmpty()){
            return "Enter a valid URL for the game banner.";
        }
        if(gameModel.getReleaseDate().isEmpty()){
            return "Enter a synopsis.";
        }
        if(gameModel.getSynopsis().isEmpty()){
            return "Enter a synopsis.";
        }
        if(gameModel.getGenres() == null){
            return "Enter valid genres: Action, Action-Adventure, Adventure, Card Game, Casual, City Builder, Comedy, Dungeon Crawler, Exploration, Fantasy, Fighting, First Person, Horror, Indie, Moba, Music, Narration, Open World, Party, Platformer, Puzzle, Racing, Retro, Rogue-Lite, RTS, Shooter, Space, Stealth, Survival, Tower Defense, Trivia, Turn-Based, Turn-Based, RPG, Simulation, Strategy, Sports, Battle Royale.";
        }
        if(gameModel.getPlatforms() == null){
            return "Enter a valid platform: PS5, PS4, Xbox 360, Xbox One, PC, Nintendo Switch, Android, iOS.";
        }

        gameModel.setAddedTime(dateFormat.format(calendar.getTime()));

        return gameRepository.save(gameModel);

    }

    public Object updateGame(UUID id, GameRecordDto gameRecordDto){
        Optional<Game> gameO = gameRepository.findById(id);
        if(gameO.isEmpty()){
            return "Game not found";
        }

        var gameModel = gameO.get();
        BeanUtils.copyProperties(gameRecordDto, gameModel);

        if(gameModel.getTitle().isEmpty()){
            return "Enter a valid Title.";
        }
        if(gameModel.getUrlImage().isEmpty()){
            return "Enter a valid URL for the game image.";
        }
        if(gameModel.getUrlBanner().isEmpty()){
            return "Enter a valid URL for the game banner.";
        }
        if(gameModel.getReleaseDate().isEmpty()){
            return "Enter a synopsis.";
        }
        if(gameModel.getSynopsis().isEmpty()){
            return "Enter a synopsis.";
        }
        if(gameModel.getGenres() == null){
            return "Enter valid genres: Action, Adventure, RPG, Simulation, Strategy, Sport, Battle Royale.";
        }
        if(gameModel.getPlatforms() == null){
            return "Enter a valid platform: PS5, PS4, Xbox 360, Xbox One, PC, Nintendo Switch, Android, iOS.";
        }

        return gameRepository.save(gameModel);
    }


}
