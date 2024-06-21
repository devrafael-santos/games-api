package com.example.gamesApi.exceptions;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException(String name) { super("Game with " + name + " already exists."); }
}
