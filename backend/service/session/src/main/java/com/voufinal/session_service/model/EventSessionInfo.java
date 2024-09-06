package com.voufinal.session_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
public class EventSessionInfo {
    private String eventId;
    private String gameId;
    private String gameType;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}
