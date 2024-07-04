package com.example.gamesApi.exceptions;

public class InvalidPlatformException extends RuntimeException{
    public InvalidPlatformException(String platform) { super ("We don't work with the platform " + platform + "."); }
}
