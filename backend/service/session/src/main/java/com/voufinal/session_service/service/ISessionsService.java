package com.voufinal.session_service.service;

import com.voufinal.session_service.dto.SessionDto;

import java.time.LocalDate;

public interface ISessionsService {
    public SessionDto findSessionByEventIdAndGameIdAndDate(String eventId, String gameId, LocalDate date);

    SessionDto createSession(SessionDto sessionDto);
}
