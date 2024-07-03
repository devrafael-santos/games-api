package com.example.gamesApi.validates;

import java.util.Set;

public class ValidateGenres {

    public static final Set<String> GENRES = Set.of(
            "ACTION", "ACTION-ADVENTURE", "ADVENTURE", "CARD GAME", "CASUAL", "CITY BUILDER",
            "COMEDY", "DUNGEON CRAWLER", "EXPLORATION", "FANTASY", "FIGHTING", "FIRST PERSON",
            "HORROR", "INDIE", "MOBA", "MUSIC", "NARRATION", "OPEN WORLD", "PARTY", "PLATFORMER",
            "PUZZLE", "RACING", "RETRO", "ROGUE-LITE", "RTS", "SHOOTER", "SPACE", "STEALTH",
            "SURVIVAL", "TOWER DEFENSE", "TRIVIA", "TURN-BASED", "TURN-BASED STRATEGY", "RPG",
            "SIMULATION", "STRATEGY", "SPORTS", "BATTLE ROYALE"
    );

    public static void validateGenre(String genre){
        if (!GENRES.contains(genre.toUpperCase())) {
            throw new IllegalArgumentException("Invalid Genre: " + genre);
        }
    }

    public ValidateGenres(String[] genres){
        for(String genre : genres){
            validateGenre(genre);
        }
    }

}
