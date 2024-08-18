package com.voufinal.player_service.service;

import com.voufinal.player_service.dto.PlayerResponse;
import com.voufinal.player_service.model.Player;
import com.voufinal.player_service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlayerServiceIpml implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addPlayer(PlayerResponse playerResponse) {
        Player player = new Player(
                null,
                playerResponse.getUserId(),
                playerResponse.getUserName(),
                this.passwordEncoder.encode(playerResponse.getPassword()),
                playerResponse.getAvatar(),
                playerResponse.getDateOfBirth(),
                playerResponse.getEmail(),
                playerResponse.getPhoneNumber(),
                playerResponse.getGender(),
                playerResponse.getFacebookAccount()
        );
        playerRepository.save(player);
    }

    @Override
    public List<PlayerResponse> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        // Convert Player entities to PlayerResponse DTOs
        return players.stream().map(player -> new PlayerResponse(
                player.getId(),
                player.getUserId(),
                player.getUserName(),
                player.getPassword(),
                player.getAvatar(),
                player.getDateOfBirth(),
                player.getEmail(),
                player.getPhoneNumber(),
                player.getGender(),
                player.getFacebookAccount()
        )).toList();
    }

    @Override
    public PlayerResponse getPlayerByID(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));
        return new PlayerResponse(
                player.getId(),
                player.getUserId(),
                player.getUserName(),
                player.getPassword(),
                player.getAvatar(),
                player.getDateOfBirth(),
                player.getEmail(),
                player.getPhoneNumber(),
                player.getGender(),
                player.getFacebookAccount()
        );
    }

    @Override
    public void updatePlayer(Long id, PlayerResponse playerResponse) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        player.setUserId(playerResponse.getUserId());
        player.setUserName(playerResponse.getUserName());
        player.setPassword(passwordEncoder.encode(playerResponse.getPassword()));
        player.setAvatar(playerResponse.getAvatar());
        player.setDateOfBirth(playerResponse.getDateOfBirth());
        player.setEmail(playerResponse.getEmail());
        player.setPhoneNumber(playerResponse.getPhoneNumber());
        player.setGender(playerResponse.getGender());
        player.setFacebookAccount(playerResponse.getFacebookAccount());

        playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));
        playerRepository.delete(player);
    }
}
