package com.voufinal.gameservice.service;

import com.voufinal.gameservice.model.Game;
import com.voufinal.gameservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game findGameByIdGame(Long idGame) {
        try {
            return gameRepository.findByIdGame(idGame);
        } catch (Exception e) {
            return null;
        }
    }

    public Game findGameByIdEvent(Long idEvent) {
        return gameRepository.findByIdEvent(idEvent);
    }
}
