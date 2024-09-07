package com.voufinal.turnservice.controller;

import com.voufinal.turnservice.common.*;
import com.voufinal.turnservice.entity.CreateTurnRequest;
import com.voufinal.turnservice.service.TurnService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/turn")
@CrossOrigin
public class TurnController {
    private final TurnService turnService;

    @Autowired
    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createTurn(@RequestBody CreateTurnRequest createTurnRequest) {
        try {
            Object result = turnService.createTurn(createTurnRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreatedResponse("Turn created successfully", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BadRequest(e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getListTurns() {
        try {
            Object result = turnService.getListTurns();
            return ResponseEntity.ok(new SuccessResponse("List of turns", HttpStatus.OK, result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @GetMapping("/{idPlaySession}")
    public ResponseEntity<?> getPlaySessionById(@PathVariable Long idPlaySession) {
        try {
            Object result = turnService.getPlaySessionById(idPlaySession);
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Play session not found"));
            }
            return ResponseEntity.ok(new SuccessResponse("Play session found", HttpStatus.OK, result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @GetMapping("/{idPlayer}/{idGame}")
    public ResponseEntity<?> getTurn(@PathVariable Long idPlayer, @PathVariable Long idGame) {
        try {
            Object result = turnService.getTurn(idPlayer, idGame);
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Turn not found"));
            }
            return ResponseEntity.ok(new SuccessResponse("Turn found", HttpStatus.OK, result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @PutMapping("/{idPlaySession}")
    public ResponseEntity<?> updatePlaySession(@PathVariable Long idPlaySession) {
        try {
            Object result = turnService.updatePlaySession(idPlaySession);
            return ResponseEntity.ok(new SuccessResponse("Play session updated successfully", HttpStatus.OK, result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @PutMapping("/{idPlayer}/{idGame}")
    public ResponseEntity<?> updateTurn(@PathVariable Long idPlayer, @PathVariable Long idGame) {
        try {
            Object result = turnService.updateTurn(idPlayer, idGame);
            return ResponseEntity.ok(new SuccessResponse("Turn updated successfully", HttpStatus.OK, result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @DeleteMapping("/{idPlaySession}")
    public ResponseEntity<?> deletePlaySession(@PathVariable Long idPlaySession) {
        try {
            turnService.deletePlaySession(idPlaySession);
            return ResponseEntity.ok(new SuccessResponse("Play session deleted successfully", HttpStatus.OK, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @DeleteMapping("/{idPlayer}/{idGame}")
    public ResponseEntity<?> deleteTurn(@PathVariable Long idPlayer, @PathVariable Long idGame) {
        try {
            turnService.deleteTurn(idPlayer, idGame);
            return ResponseEntity.ok(new SuccessResponse("Turn deleted successfully", HttpStatus.OK, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }
}