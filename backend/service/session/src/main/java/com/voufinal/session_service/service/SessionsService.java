package com.voufinal.session_service.service;

import com.voufinal.session_service.dto.SessionDto;
import com.voufinal.session_service.entity.SessionEntity;
import com.voufinal.session_service.exception.NotFoundException;
import com.voufinal.session_service.mapper.SessionMapper;
import com.voufinal.session_service.repository.SessionsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class SessionsService implements ISessionsService {
    private SessionsRepository sessionsRepository;
    private SessionMapper sessionMapper;

    @Override
    public SessionDto findSessionByEventIdAndGameIdAndDate(String eventId, String gameId, LocalDate date) {
        SessionEntity sessionEntity =
                sessionsRepository.findSessionByEventIdAndGameIdAndDate(eventId, gameId, date).orElseThrow(
                        () -> new NotFoundException("Sessions", "date", date.toString())
                );

        log.info("SessionEntity: {}", sessionEntity.getId());

        return sessionMapper.toSessionDto(sessionEntity);
    }

    @Override
    public SessionDto createSession(SessionDto sessionDto) {
        SessionEntity sessionEntity = sessionMapper.toSession(sessionDto);
        return sessionMapper.toSessionDto(sessionsRepository.save(sessionEntity));
    }
}
