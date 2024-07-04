package com.example.gamesApi.infra;

import com.example.gamesApi.exceptions.GameAlreadyExistsException;
import com.example.gamesApi.exceptions.GameNotFoundException;
import com.example.gamesApi.exceptions.InvalidGenreException;
import com.example.gamesApi.exceptions.InvalidPlatformException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    private ResponseEntity<String> gameNotFoundHandler(GameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(GameAlreadyExistsException.class)
    private ResponseEntity<String> gameAlreadyExistsHandler(GameAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidGenreException.class)
    private ResponseEntity<String> InvalidGenreHandler(InvalidGenreException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPlatformException.class)
    private ResponseEntity<String> InvalidPlatformException(InvalidPlatformException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
