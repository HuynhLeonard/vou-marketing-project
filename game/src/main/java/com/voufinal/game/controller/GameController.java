package com.voufinal.game.controller;

import com.voufinal.game.dto.GameDto;
import com.voufinal.game.dto.ResponseDto;
import com.voufinal.game.service.GameService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
public class GameController {

    private final GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<GameDto>> getAllGame() {
        List<GameDto> gameList = gameService.getAllGames();
        return ResponseEntity.ok(gameList);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Long id) {
        GameDto game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/games")
    public ResponseEntity<ResponseDto> createGame(@RequestBody GameDto gameDto) {
        gameService.createGame(gameDto);
        ResponseDto res = new ResponseDto(HttpStatus.CREATED, "Game created successfully");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/games")
    public ResponseEntity<ResponseDto> updateGame(@RequestBody GameDto gameDto) {
        gameService.updateGame(gameDto);
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Game updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<ResponseDto> deleteGame(@PathVariable("id") Long id) {
        gameService.deleteGame(id);
        ResponseDto res = new ResponseDto(HttpStatus.OK, "Game deleted successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
