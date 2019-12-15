package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;
}
