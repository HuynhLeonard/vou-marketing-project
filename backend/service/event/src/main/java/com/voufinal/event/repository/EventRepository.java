package com.voufinal.event.repository;

import com.voufinal.event.entity.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String>, EventRepositoryCustom {
    List<Event> findByIds(List<String> ids);
}
