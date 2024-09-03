package com.voufinal.event.repository;

import java.util.List;

import com.voufinal.event.entity.Event;

public interface EventRepositoryCustom {
    List<Event> findByIds(List<String> ids);
}
