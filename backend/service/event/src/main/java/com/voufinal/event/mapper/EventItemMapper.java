package com.voufinal.event.mapper;

import com.voufinal.event.dto.EventItemDto;
import com.voufinal.event.entity.EventItem;

import org.springframework.stereotype.Service;

@Service
public class EventItemMapper {

    // Convert EventItem to EventItemDto
    public static EventItemDto toDto(EventItem eventItem) {
        if (eventItem == null) {
            return null;
        }

        EventItemDto eventItemDto = new EventItemDto();
        eventItemDto.setEvent(EventMapper.toDto(eventItem.getEvent()));
        eventItemDto.setItem(ItemMapper.toDto(eventItem.getItem()));

        return eventItemDto;
    }

    // Convert EventItemDto to EventItem
    public static EventItem toEntity(EventItemDto dto) {
        if (dto == null) {
            return null;
        }

        EventItem eventItem = new EventItem();
        eventItem.setEvent(EventMapper.toEntity(dto.getEvent()));
        eventItem.setItem(ItemMapper.toEntity(dto.getItem()));

        return eventItem;
    }
}