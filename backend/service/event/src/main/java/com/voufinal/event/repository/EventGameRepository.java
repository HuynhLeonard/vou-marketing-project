package com.voufinal.event.repository;

import com.voufinal.event.entity.EventGame;
import com.voufinal.event.model.EventGameId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventGameRepository extends JpaRepository<EventGame, EventGameId>, EventGameRepositoryCustom {
    EventGame findByEventAndGame(String eventId, Long gameId);
    List<EventGame> findByEvent(String eventId);
}