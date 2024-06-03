package com.example.gamesApi.exceptions;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException(String massage) { super(massage); }
}
