package com.example.gamesApi.controllers;


import com.example.gamesApi.dto.GameDTO;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/search")
    public ResponseEntity<List<GameModel>> searchGames(@RequestParam("title") String title) {
        List<GameModel> games = gameService.searchGames(title);

        for(GameModel game : games){
            game.add(linkTo(methodOn(GameController.class).getOne(game.getId())).withSelfRel());
        }

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<GameModel>> searchByGenres(@RequestParam("genre") String genre){

        List<GameModel> games = gameService.searchByGenre(genre);

        for(GameModel game : games){
            game.add(linkTo(methodOn(GameController.class).getOne(game.getId())).withSelfRel());
        }

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @GetMapping("/platforms")
    public ResponseEntity<List<GameModel>> searchByPlatforms(@RequestParam("platform") String platform) {

        List<GameModel> games = gameService.searchByPlatforms(platform);

        for(GameModel game : games){
            game.add(linkTo(methodOn(GameController.class).getOne(game.getId())).withSelfRel());
        }

        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getOne(@PathVariable(value = "id") UUID id){

        GameModel game = gameService.getGame(id);
        GameDTO gameDto = new GameDTO(game);

        gameDto.add(linkTo(methodOn(GameController.class).getAll()).withRel("Game List"));

        return ResponseEntity.status(HttpStatus.OK).body(gameDto);

    }

    @PostMapping()
    public ResponseEntity<GameDTO> saveOne(@RequestBody @Valid GameDTO gameDto){

        GameDTO game = new GameDTO(gameService.createGame(gameDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable(value = "id") UUID id){
        gameService.deleteGame(id);

        return ResponseEntity.status(HttpStatus.OK).body("Game deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateOne(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid GameDTO gameDto){

        GameDTO game = new GameDTO(gameService.updateGame(id, gameDto));

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


}
