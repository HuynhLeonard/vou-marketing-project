package com.voufinal.player_service.service;

import com.voufinal.player_service.dto.PlayerResponse;
import java.util.List;

public interface PlayerService {
    void addPlayer(PlayerResponse playerResponse);

    List<PlayerResponse> getAllPlayers();

    PlayerResponse getPlayerByID(Long id);

    void updatePlayer(Long id, PlayerResponse playerResponse);

    void deletePlayer(Long id);
}
