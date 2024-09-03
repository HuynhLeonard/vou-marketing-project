package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> findAllPlayer();

    Player findPlayerById(String customerId);

    void savePlayer(Player player);

    void updatePlayer(Player player);

    void deletePlayerById(String customerId);

    Player findPlayerByPhoneNumber(String phoneNumber);

    Player findPlayerByEmail(String email);
}
