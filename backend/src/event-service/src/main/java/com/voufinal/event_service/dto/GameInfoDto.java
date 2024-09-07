package com.voufinal.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameInfoDto {
    private Long gameId;
    private String name;
    private String gameType;
    private List<QuizDto> quiz;
    private Long eventId;
    private Timestamp startedAt;
}
