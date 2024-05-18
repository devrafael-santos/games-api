package com.example.gamesApi.controllers;


import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.exception.ResourceNotFoundException;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<?> getGames() {
        List<GameModel> games = gameService.getAllGame();

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GameRecordDto> getGame(@PathVariable(value = "id") UUID id){
        GameRecordDto gameDTO = gameService.getOneGame(id);

        if(gameDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(gameDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveGame(@RequestBody @Valid GameModel gameModel){
        try {
            GameRecordDto gameRecordDto = gameService.createGame(gameModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(gameRecordDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteGame(@PathVariable(value = "id") UUID id){
        try {
            gameService.deleteGame(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Jogo deletado com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid GameModel gameModel){
        try {
            GameRecordDto gameRecordDto = gameService.updateGame(id, gameModel);
            return ResponseEntity.status(HttpStatus.OK).body(gameRecordDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
