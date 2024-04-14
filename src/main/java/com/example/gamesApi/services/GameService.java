package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.entities.Game;
import com.example.gamesApi.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    @Transactional
    public Object createGame(GameRecordDto gameRecordDto){
        var gameModel = new Game();
        BeanUtils.copyProperties(gameRecordDto, gameModel);

        if(gameModel.getPlatforms() == null){
            return "Enter a valid platform: PS5, PS4, Xbox 360, Xbox One, PC, Nintendo Switch, Android, iOS.";
        }
        if(gameModel.getTitle().isEmpty()){
            return "Enter a valid Title.";
        }

        return gameRepository.save(gameModel);

    }

    public Object updateGame(UUID id, GameRecordDto gameRecordDto){
        Optional<Game> gameO = gameRepository.findById(id);
        if(gameO.isEmpty()){
            return "Game not found";
        }

        var gameModel = gameO.get();
        BeanUtils.copyProperties(gameRecordDto, gameModel);

        if(gameModel.getPlatforms() == null){
            return "Enter a valid platform: PS5, PS4, Xbox 360, Xbox One, PC, Nintendo Switch, Android, iOS.";
        }
        if(gameModel.getTitle().isEmpty()){
            return "Enter a valid Title.";
        }

        return gameRepository.save(gameModel);
    }


}
