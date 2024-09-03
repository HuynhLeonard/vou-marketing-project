package com.voufinal.user_service.controller;

import com.voufinal.user_service.model.Player;
import com.voufinal.user_service.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getall")
    public List<Player> getAllPlayer() {
        return playerService.findAllPlayer();
    }

    @GetMapping("/getbyId/{playerId}")
    public Player getPlayerById(@PathVariable("playerId") String playerId) {
        return playerService.findPlayerById(playerId);
    }

    @PostMapping("/addplayer")
    public Player addPlayer(@RequestBody Player player) {
        player.setId("0");
        playerService.savePlayer(player);
        return player;
    }

    @PatchMapping("/update/{playerId}")
    public Player updatePlayer(@PathVariable String playerId, @RequestBody Player player) {
        player.setId(playerId);
        playerService.updatePlayer(player);
        return player;
    }

    @DeleteMapping("/delete/{playerId}")
    public String deletePlayer(@PathVariable("playerId") String playerId) {
        playerService.deletePlayerById(playerId);
        return "Player is deleted!";
    }

    @GetMapping("/phonenumber/{phoneNumber}")
    public Player getPlayerByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return playerService.findPlayerByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public Player getPlayerByEmail(@PathVariable("email") String email) {
        return playerService.findPlayerByEmail(email);
    }
}
