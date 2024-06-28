package com.example.gamesApi.controllers;

import com.example.gamesApi.repositories.IGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ameControllerTest {
    @InjectMocks
    private GameController gameController;

    @Mock
    private IGameRepository IGameRepositoryMock;


    @Test
    void test(){

    }
}