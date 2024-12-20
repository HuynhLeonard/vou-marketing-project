package com.voufinal.event.mapper;

import com.voufinal.event.dto.EventDto;
import com.voufinal.event.entity.Event;

import org.springframework.stereotype.Service;

@Service
public class EventMapper {

    // Convert Event to EventDto
    public static EventDto toDto(Event event) {
        if (event == null) {
            return null;
        }

        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setImage(event.getImage());
        eventDto.setNumberOfVoucher(event.getNumberOfVoucher());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());

        return eventDto;
    }

    // Convert EventDto to Event
    public static Event toEntity(EventDto dto) {
        if (dto == null) {
            return null;
        }

        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setImage(dto.getImage());
        event.setNumberOfVoucher(dto.getNumberOfVoucher());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());

        return event;
    }
}
