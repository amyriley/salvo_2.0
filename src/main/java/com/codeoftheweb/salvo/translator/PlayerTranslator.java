package com.codeoftheweb.salvo.translator;

import com.codeoftheweb.salvo.dto.PlayerDto;
import com.codeoftheweb.salvo.model.Player;

import java.util.List;

import static com.codeoftheweb.salvo.dto.PlayerDto.Builder.aPlayerDto;
import static java.util.stream.Collectors.toList;

public class PlayerTranslator {

    public static List<PlayerDto> toDtos(List<Player> models) {
        return models.stream()
                .map(m -> toDto(m))
                .collect(toList());
    }

    public static PlayerDto toDto(Player player) {
        return aPlayerDto()
                .withId(player.getId())
                .withEmail(player.getUserName())
                .build();
    }

    public static Player toModel(PlayerDto dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setUserName(dto.getEmail());
        return player;
    }
}
