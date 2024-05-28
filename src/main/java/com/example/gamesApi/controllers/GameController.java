package com.example.gamesApi.controllers;


import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/games")
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping()
    public ResponseEntity<List<GameModel>> getGames() {
        List<GameModel> games = gameService.getAllGames();

        for(GameModel game : games){
            game.add(linkTo(methodOn(GameController.class).getGame(game.getId())).withSelfRel());
        }

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }


    @GetMapping("/{id}")
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

    @PostMapping()
    public ResponseEntity<Object> saveGame(@RequestBody @Valid GameRecordDto gameRecordDto){
        var game = gameService.create(gameRecordDto);

        if(game.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(game);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteGame(@PathVariable(value = "id") UUID id){
        var game = gameService.delete(id);

        if(game == "Game not found."){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(game);
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid GameRecordDto gameRecordDto){

        var game = gameService.update(id, gameRecordDto);

        if(game.getClass() == String.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(game);
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


}
