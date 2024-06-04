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
import org.springframework.web.client.ResourceAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/games")
@RestController
public class GameController implements Serializable {
    @Autowired
    private GameService gameService;

    @GetMapping()
    public ResponseEntity<List<GameModel>> getAll() {
        List<GameModel> games = gameService.getAllGames();

        for(GameModel game : games){
            game.add(linkTo(methodOn(GameController.class).getOne(game.getId())).withSelfRel());
        }

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") UUID id){

        GameRecordDto gameDto;

        try {
        var game = gameService.getGame(id);

        gameDto = new GameRecordDto(game);

        gameDto.add(linkTo(methodOn(GameController.class).getAll()).withRel("Game List"));

        } catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeException");
        }

        return ResponseEntity.status(HttpStatus.OK).body(gameDto);

    }

    @PostMapping()
    public ResponseEntity<?> saveOne(@RequestBody @Valid GameRecordDto gameRecordDto){
        GameRecordDto game;

        try {

        game = new GameRecordDto(gameService.createGame(gameRecordDto));

        } catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeException");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable(value = "id") UUID id){

        try {
        gameService.deleteGame(id);

        } catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeException");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Game deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid GameRecordDto gameRecordDto){
        GameRecordDto game;

        try {

        game = new GameRecordDto(gameService.updateGame(id, gameRecordDto));
        } catch (RuntimeException e){

            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeException");
        }

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


}
