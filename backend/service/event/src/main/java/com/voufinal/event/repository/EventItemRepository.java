package com.voufinal.event.repository;

import com.voufinal.event.entity.EventItem;
import com.voufinal.event.model.EventItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventItemRepository extends JpaRepository<EventItem, EventItemId>, EventItemRepositoryCustom {
    EventItem findByEventAndItem(String eventId, String itemId);
}