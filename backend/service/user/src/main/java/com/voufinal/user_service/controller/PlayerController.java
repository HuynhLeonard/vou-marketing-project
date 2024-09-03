package com.voufinal.user_service.controller;

import com.voufinal.user_service.model.Player;
import com.voufinal.user_service.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getalls")
    public List<Player> getAllPlayer() {
        return playerService.findAllPlayer();
    }

    @GetMapping("/getbyId/{playerId}")
    public Player getCustomerById(@PathVariable("playerId") String playerId) {
        return playerService.findPlayerById(playerId);
    }

    @PostMapping("/addplayer")
    public Player addCustomer(@RequestBody Player player) {
        player.setId("0");
        playerService.savePlayer(player);
        return player;
    }

    @PatchMapping("/update")
    public Player updatePlayer(@RequestBody Player player) {
        playerService.updatePlayer(player);
        return player;
    }

    @DeleteMapping("/delete/{playerId}")
    public String deleteCustomer(@PathVariable("playerId") String playerId) {
        playerService.deletePlayerById(playerId);
        return "Customer deleted";
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
