package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame() {
        Game game = new Game();
        gameRepository.save(game);

        return game;
    }

    public List<Game> getAllGames() {
        return gameRepository
                .findAll()
                .stream()
                .collect(toList());
    }

    public void joinGame() {

    }
}
