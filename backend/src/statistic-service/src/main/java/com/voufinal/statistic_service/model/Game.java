package com.voufinal.statistic_service.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class Game {
    private Long idGame;
    private String name;
    private String imageUrl;
    private String type;
    private Long idEvent;
    private String instruction;
    private Timestamp startedAt;
}
