package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.service.GameService;
import com.codeoftheweb.salvo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public ResponseEntity<String> createGame() {
        gameService.createGame();
        return new ResponseEntity<>("Game created", HttpStatus.CREATED);
    }

    @RequestMapping("/games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @RequestMapping(value = "/game/{gameId}/players", method = RequestMethod.POST)
    public ResponseEntity<String> joinGame(@PathVariable Long gameId, Authentication authentication) {
        gameService.joinGame();
        return new ResponseEntity<>("Game joined", HttpStatus.OK);
    }
}
