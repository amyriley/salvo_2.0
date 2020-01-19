package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(String username, String password) {
        Player player = new Player(username, password);
        playerRepository.save(player);

        return player;
    }

    public Player getPlayer(String username) {
        return playerRepository.findByUsername(username);
    }

    public List<Player> getAllPlayers() {
        return playerRepository
                .findAll()
                .stream()
                .collect(toList());
    }
}
