package com.voufinal.event.common;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameStartTime {
    private String gameId;
    private LocalDateTime startTime;
}
