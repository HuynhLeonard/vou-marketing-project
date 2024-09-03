package com.voufinal.event.repository;

import java.util.List;

import com.voufinal.event.entity.EventGame;

public interface EventGameRepositoryCustom {
    EventGame findByEventAndGame(String eventId, Long gameId);
    List<EventGame> findByEvent(String eventId);
}