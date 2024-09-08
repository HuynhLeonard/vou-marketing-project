package com.voufinal.mc_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class GameInfoDto {
    private Long gameId;
    private String name;
    private String gameType;
    private Long eventId;
    private Timestamp startedAt;
    private List<QuizDto> quiz;
}
