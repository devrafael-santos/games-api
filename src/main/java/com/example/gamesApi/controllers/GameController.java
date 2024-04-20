package com.example.gamesApi.controllers;

import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<GameModel>> getGames() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAll());
    }

    /*
    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGame(@PathVariable(value = "id") UUID id){
        Optional<GameModel> gameO = gameRepository.findById(id);
        if(gameO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameO.get());
    }
    */

    @PostMapping("/games")
    public ResponseEntity<Object> saveGame(@RequestBody @Valid GameRecordDto gameRecordDto){
        var response = gameService.create(gameRecordDto);

        if(response.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
    @DeleteMapping("/games/{id}")
    public ResponseEntity<Object> DeleteGame(@PathVariable(value = "id") UUID id){
        Optional<GameModel> gameO = gameRepository.findById(id);
        if(gameO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }

        var gameTitle = gameO.get().getTitle();

        gameRepository.delete(gameO.get());
        return ResponseEntity.status(HttpStatus.OK).body("The game "+ gameTitle +" was deleted successfully");
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid GameRecordDto gameRecordDto){

        Optional<GameModel> gameO = gameRepository.findById(id);
        if(gameO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }

        var gameModel = gameO.get();
        BeanUtils.copyProperties(gameRecordDto, gameModel);

        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.save(gameModel));
    }

    */
}
