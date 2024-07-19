package com.example.gamesApi.validates;

import com.example.gamesApi.exceptions.InvalidGenreException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateGenres {

    public static final Set<String> GENRES = Set.of(
            "ACTION", "ACTION-ADVENTURE", "ADVENTURE", "CARD-GAME", "CASUAL", "CITY-BUILDER",
            "COMEDY", "DUNGEON-CRAWLER", "EXPLORATION", "FANTASY", "FIGHTING", "FIRST-PERSON",
            "HORROR", "INDIE", "MOBA", "MUSIC", "NARRATION", "OPEN-WORLD", "PARTY", "PLATFORMER",
            "PUZZLE", "RACING", "RETRO", "ROGUE-LITE", "RTS", "SHOOTER", "SPACE", "STEALTH",
            "SURVIVAL", "TOWER-DEFENSE", "TRIVIA", "TURN-BASED", "TURN-BASED-STRATEGY", "RPG",
            "SIMULATION", "STRATEGY", "SPORTS", "BATTLE-ROYALE"
    );

    public static String validateGenre(String genre){
        if (!GENRES.contains(genre)) {
            throw new InvalidGenreException(genre);
        } else {
        return genre.toUpperCase();
        }
    }

    public static Set<String> validate(Set<String> genres){
        Set<String> validatedGenres = new HashSet<>();
        for(String genre : genres){
            validatedGenres.add(validateGenre(genre.toUpperCase()));
        }
        return validatedGenres;
    }

}
