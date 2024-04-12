package com.example.gamesApi.controllers;

import com.example.gamesApi.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class GameControllerTest {
    @InjectMocks
    private GameController gameController;

    @Mock
    private GameRepository gameRepositoryMock;


    @Test
    void test(){

    }
}