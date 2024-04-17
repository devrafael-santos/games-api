package com.example.gamesApi.controllers;

import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.entities.Game;
import com.example.gamesApi.repositories.GameRepository;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findAll());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGame(@PathVariable(value = "id") UUID id){
        Optional<Game> gameO = gameRepository.findById(id);
        if(gameO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameO.get());
    }

    @PostMapping("/games")
    public ResponseEntity<Object> saveGame(@RequestBody @Valid GameRecordDto gameRecordDto){
        var responseGameService = gameService.createGame(gameRecordDto);

        if(responseGameService.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseGameService);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseGameService);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Object> DeleteGame(@PathVariable(value = "id") UUID id){
        if(!gameRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }
        String gameName = gameRepository.findById(id).get().getTitle();
        gameRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("The game "+ gameName +" was deleted successfully");
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value = "id") UUID id,
                                         @RequestBody GameRecordDto gameRecordDto){
        var responseGameService = gameService.updateGame(id, gameRecordDto);

        if(responseGameService.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseGameService);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseGameService);
    }
}
