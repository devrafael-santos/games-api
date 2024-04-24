package com.example.gamesApi.controllers;


import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<GameModel>> getGames() {
        List<GameModel> games = gameService.getAll();

        for(GameModel game : games){
            game.add(linkTo(methodOn(GameController.class).getGame(game.getId())).withSelfRel());
        }

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }


    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGame(@PathVariable(value = "id") UUID id){
        var gameO = gameService.getOne(id);

        if(gameO.getClass().equals(String.class)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gameO);
        }

        GameModel game = new GameModel();

        BeanUtils.copyProperties(gameO, game);

        game.add(linkTo(methodOn(GameController.class).getGames()).withRel("Game List"));


        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PostMapping("/games")
    public ResponseEntity<Object> saveGame(@RequestBody @Valid GameRecordDto gameRecordDto){
        var game = gameService.create(gameRecordDto);

        if(game.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(game);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }


    @DeleteMapping("/games/{id}")
    public ResponseEntity<Object> DeleteGame(@PathVariable(value = "id") UUID id){
        var game = gameService.delete(id);

        if(game == "Game not found."){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(game);
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid GameRecordDto gameRecordDto){

        var game = gameService.update(id, gameRecordDto);

        if(game.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(game);
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


}
