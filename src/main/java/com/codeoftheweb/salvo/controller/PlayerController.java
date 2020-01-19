package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.dto.PlayerDto;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.service.PlayerService;
import com.codeoftheweb.salvo.translator.PlayerTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public ResponseEntity<String> createPlayer(@RequestParam String username, @RequestParam String password) {

        if (username.isEmpty()) {
            return new ResponseEntity<>("No username given", HttpStatus.FORBIDDEN);
        }

        Player player = playerService.getPlayer(username);

        if (player != null) {
            return new ResponseEntity<>("Username already used", HttpStatus.CONFLICT);
        }

        playerService.createPlayer(username, password);

        return new ResponseEntity<>("Named added", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<PlayerDto> getAllPlayers() {
        return PlayerTranslator.toDtos(playerService.getAllPlayers());
    }

}
