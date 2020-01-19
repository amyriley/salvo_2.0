package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerService {

    @Autowired
    GamePlayerRepository gamePlayerRepository;

    public GamePlayer createGamePlayer() {
        GamePlayer gamePlayer = new GamePlayer();
        gamePlayerRepository.save(gamePlayer);

        return gamePlayer;
    }

}
