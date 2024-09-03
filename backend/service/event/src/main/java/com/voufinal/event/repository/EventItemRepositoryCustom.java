package com.voufinal.event.repository;

import com.voufinal.event.entity.EventItem;

public interface EventItemRepositoryCustom {
    EventItem findByEventAndItem(String eventId, String itemId);
}