package com.example.gamesApi.exceptions;

public class InvalidGenreException extends RuntimeException{
    public InvalidGenreException(String genre) { super ("We don't work with the genre " + genre + "."); }
}
