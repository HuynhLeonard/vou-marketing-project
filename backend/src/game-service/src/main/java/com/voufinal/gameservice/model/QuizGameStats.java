package com.voufinal.gameservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizGameStats {
    private Long idQuizGameStat;

    private Long idEvent;

    private Long idGame;

    private Long numberOfParticipants;
}