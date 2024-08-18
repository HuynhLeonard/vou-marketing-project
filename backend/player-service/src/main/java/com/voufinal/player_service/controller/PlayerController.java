package com.voufinal.player_service.controller;

import com.voufinal.player_service.dto.PlayerResponse;
import com.voufinal.player_service.dto.LoginResponse;
import com.voufinal.player_service.dto.LoginPlayerResponse;
import com.voufinal.player_service.model.Player;
import com.voufinal.player_service.repository.PlayerRepository;
import com.voufinal.player_service.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public String createPlayer(@RequestBody PlayerResponse playerResponse){
        playerService.addPlayer(playerResponse);
        return "Create player succeed";
    }

    @GetMapping()
    public List<PlayerResponse> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/get")
    public PlayerResponse getPlayerById(@RequestParam Long id){
        return playerService.getPlayerByID(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updatePlayer(@PathVariable Long id, @RequestBody PlayerResponse playerResponse){
        playerService.updatePlayer(id, playerResponse);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}