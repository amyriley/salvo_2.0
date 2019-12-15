package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerController {

    @Autowired
    private PlayerService playerService;
}
