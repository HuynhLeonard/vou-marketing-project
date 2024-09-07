package com.voufinal.turnservice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTurnRequest {
    // Getter và Setter cho idPlayer
    private Long idPlayer;
    // Getter và Setter cho idGame
    private Long idGame;
    // Getter và Setter cho score
    private int score;
    // Getter và Setter cho turns
    private int turns;

}
