package com.example.gamesApi.controllers;


import com.example.gamesApi.dto.GameDto;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/games")
@RestController
public class GameController implements Serializable {

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

        GameDto gameDto;

        try {
        var game = gameService.getGame(id);

        gameDto = new GameDto(game);

        gameDto.add(linkTo(methodOn(GameController.class).getAll()).withRel("Game List"));

        } catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeException");
        }

        return ResponseEntity.status(HttpStatus.OK).body(gameDto);

    }

    @PostMapping()
    public ResponseEntity<?> saveOne(@RequestBody @Valid GameDto gameDto){
        GameDto game;

        try {

        game = new GameDto(gameService.createGame(gameDto));

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
                                         @RequestBody @Valid GameDto gameDto){
        GameDto game;

        try {

        game = new GameDto(gameService.updateGame(id, gameDto));
        } catch (RuntimeException e){

            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeException");
        }

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


}
