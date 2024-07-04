package com.example.gamesApi.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {super("Game not found.");}
}
