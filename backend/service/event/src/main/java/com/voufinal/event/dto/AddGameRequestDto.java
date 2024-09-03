package com.voufinal.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.voufinal.event.common.GameId_StartTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddGameRequestDto {
    private String eventId;
    private List<GameId_StartTime> gameId_StartTimes;
}