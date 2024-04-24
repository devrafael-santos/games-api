package com.example.gamesApi.services;

import com.example.gamesApi.dto.GameRecordDto;
import com.example.gamesApi.models.GameModel;
import com.example.gamesApi.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    final Calendar calendar = Calendar.getInstance();

    public Object getOne(UUID id){
        Optional<GameModel> gameO = gameRepository.findById(id);

        if(gameO.isEmpty()){
            return "Game not found.";
        }

        return gameO.get();
    }


    public List<GameModel> getAll(){
        return gameRepository.findAll();
    }

    public Object create(GameRecordDto gameRecordDto) {

        var genres = gameRecordDto.getGenres();
        var platforms = gameRecordDto.getPlatforms();

        //verifying the genres attribute
        for (int i = 0; i <= genres.length - 1; i++) {
            if (genres[i].equalsIgnoreCase("Action")
                    || genres[i].equalsIgnoreCase("Action-Adventure")
                    || genres[i].equalsIgnoreCase("Adventure")
                    || genres[i].equalsIgnoreCase("Card Game")
                    || genres[i].equalsIgnoreCase("Casual")
                    || genres[i].equalsIgnoreCase("City Builder")
                    || genres[i].equalsIgnoreCase("Comedy")
                    || genres[i].equalsIgnoreCase("Dungeon Crawler")
                    || genres[i].equalsIgnoreCase("Exploration")
                    || genres[i].equalsIgnoreCase("Fantasy")
                    || genres[i].equalsIgnoreCase("Fighting")
                    || genres[i].equalsIgnoreCase("First Person")
                    || genres[i].equalsIgnoreCase("Horror")
                    || genres[i].equalsIgnoreCase("Indie")
                    || genres[i].equalsIgnoreCase("Moba")
                    || genres[i].equalsIgnoreCase("Music")
                    || genres[i].equalsIgnoreCase("Narration")
                    || genres[i].equalsIgnoreCase("Open World")
                    || genres[i].equalsIgnoreCase("Party")
                    || genres[i].equalsIgnoreCase("Platformer")
                    || genres[i].equalsIgnoreCase("Puzzle")
                    || genres[i].equalsIgnoreCase("Racing")
                    || genres[i].equalsIgnoreCase("Retro")
                    || genres[i].equalsIgnoreCase("Rogue-Lite")
                    || genres[i].equalsIgnoreCase("RTS")
                    || genres[i].equalsIgnoreCase("Shooter")
                    || genres[i].equalsIgnoreCase("Space")
                    || genres[i].equalsIgnoreCase("Stealth")
                    || genres[i].equalsIgnoreCase("Survival")
                    || genres[i].equalsIgnoreCase("Tower Defense")
                    || genres[i].equalsIgnoreCase("Trivia")
                    || genres[i].equalsIgnoreCase("Turn-Based")
                    || genres[i].equalsIgnoreCase("Turn-Based Strategy")
                    || genres[i].equalsIgnoreCase("RPG")
                    || genres[i].equalsIgnoreCase("Simulation")
                    || genres[i].equalsIgnoreCase("Strategy")
                    || genres[i].equalsIgnoreCase("Sports")
                    || genres[i].equalsIgnoreCase("Battle Royale")) {

            } else {
                return "Enter valid genres: Action, Action-Adventure, Adventure, Card Game, Casual, City Builder, Comedy, Dungeon Crawler, Exploration, Fantasy, Fighting, First Person, " +
                        "Horror, Indie, Moba, Music, Narration, Open World, Party, Platformer, Puzzle, Racing, Retro, Rogue-Lite, RTS, Shooter, Space, Stealth, Survival, Tower Defense, " +
                        "Trivia, Turn-Based, Turn-Based Strategy, RPG, Simulation, Strategy, Sports, Battle Royale.";
            }
        }


        //verifying the genres attribute
        for(int i=0; i <= platforms.length-1; i++){
            if(platforms[i].equalsIgnoreCase("PS5")
                    || platforms[i].equalsIgnoreCase("PS4")
                    || platforms[i].equalsIgnoreCase("Xbox 360")
                    || platforms[i].equalsIgnoreCase("Xbox One")
                    || platforms[i].equalsIgnoreCase("PC")
                    || platforms[i].equalsIgnoreCase("Nintendo Switch")
                    || platforms[i].equalsIgnoreCase("Android")
                    || platforms[i].equalsIgnoreCase("iOS")){

            } else {
                return "Enter valid platforms: PS5, PS4, Xbox 360, Xbox One, PC, Nintendo Switch, Android, iOS";
            }
        }


        GameModel gameModel = new GameModel(gameRecordDto);

        gameModel.setAddedTime(dateFormat.format(calendar.getTime()));

        return gameRepository.save(gameModel);
    }


    public Object update(UUID id, GameRecordDto gameRecordDto){

        Optional<GameModel> gameO = gameRepository.findById(id);

        var genres = gameRecordDto.getGenres();
        var platforms = gameRecordDto.getPlatforms();
        var addedTime = gameO.get().getAddedTime();

        if(gameO.isEmpty()){
            return "Game not found";
        }

        //verifying the genres attribute
        for (int i = 0; i <= genres.length - 1; i++) {
            if (genres[i].equalsIgnoreCase("Action")
                    || genres[i].equalsIgnoreCase("Action-Adventure")
                    || genres[i].equalsIgnoreCase("Adventure")
                    || genres[i].equalsIgnoreCase("Card Game")
                    || genres[i].equalsIgnoreCase("Casual")
                    || genres[i].equalsIgnoreCase("City Builder")
                    || genres[i].equalsIgnoreCase("Comedy")
                    || genres[i].equalsIgnoreCase("Dungeon Crawler")
                    || genres[i].equalsIgnoreCase("Exploration")
                    || genres[i].equalsIgnoreCase("Fantasy")
                    || genres[i].equalsIgnoreCase("Fighting")
                    || genres[i].equalsIgnoreCase("First Person")
                    || genres[i].equalsIgnoreCase("Horror")
                    || genres[i].equalsIgnoreCase("Indie")
                    || genres[i].equalsIgnoreCase("Moba")
                    || genres[i].equalsIgnoreCase("Music")
                    || genres[i].equalsIgnoreCase("Narration")
                    || genres[i].equalsIgnoreCase("Open World")
                    || genres[i].equalsIgnoreCase("Party")
                    || genres[i].equalsIgnoreCase("Platformer")
                    || genres[i].equalsIgnoreCase("Puzzle")
                    || genres[i].equalsIgnoreCase("Racing")
                    || genres[i].equalsIgnoreCase("Retro")
                    || genres[i].equalsIgnoreCase("Rogue-Lite")
                    || genres[i].equalsIgnoreCase("RTS")
                    || genres[i].equalsIgnoreCase("Shooter")
                    || genres[i].equalsIgnoreCase("Space")
                    || genres[i].equalsIgnoreCase("Stealth")
                    || genres[i].equalsIgnoreCase("Survival")
                    || genres[i].equalsIgnoreCase("Tower Defense")
                    || genres[i].equalsIgnoreCase("Trivia")
                    || genres[i].equalsIgnoreCase("Turn-Based")
                    || genres[i].equalsIgnoreCase("Turn-Based Strategy")
                    || genres[i].equalsIgnoreCase("RPG")
                    || genres[i].equalsIgnoreCase("Simulation")
                    || genres[i].equalsIgnoreCase("Strategy")
                    || genres[i].equalsIgnoreCase("Sports")
                    || genres[i].equalsIgnoreCase("Battle Royale")) {

            } else {
                return "Enter valid genres: Action, Action-Adventure, Adventure, Card Game, Casual, City Builder, Comedy, Dungeon Crawler, Exploration, Fantasy, Fighting, First Person, " +
                        "Horror, Indie, Moba, Music, Narration, Open World, Party, Platformer, Puzzle, Racing, Retro, Rogue-Lite, RTS, Shooter, Space, Stealth, Survival, Tower Defense, " +
                        "Trivia, Turn-Based, Turn-Based Strategy, RPG, Simulation, Strategy, Sports, Battle Royale.";
            }
        }


        //verifying the genres attribute
        for(int i=0; i <= platforms.length-1; i++){
            if(platforms[i].equalsIgnoreCase("PS5")
                    || platforms[i].equalsIgnoreCase("PS4")
                    || platforms[i].equalsIgnoreCase("Xbox 360")
                    || platforms[i].equalsIgnoreCase("Xbox One")
                    || platforms[i].equalsIgnoreCase("PC")
                    || platforms[i].equalsIgnoreCase("Nintendo Switch")
                    || platforms[i].equalsIgnoreCase("Android")
                    || platforms[i].equalsIgnoreCase("iOS")){

            } else {
                return "Enter valid platforms: PS5, PS4, Xbox 360, Xbox One, PC, Nintendo Switch, Android, iOS";
            }
        }


        GameModel gameModel = new GameModel(gameRecordDto);
        gameModel.setAddedTime(addedTime);

        gameRepository.delete(gameO.get());

        return gameRepository.save(gameModel);
    }

    public Object delete(UUID id){
        Optional<GameModel> gameO = gameRepository.findById(id);

        if(gameO.isEmpty()){
            return "Game not found.";
        }

        var gameTitle = gameO.get().getTitle();

        gameRepository.delete(gameO.get());
        return "The game `"+ gameTitle +"` was deleted successfully";
    }


}