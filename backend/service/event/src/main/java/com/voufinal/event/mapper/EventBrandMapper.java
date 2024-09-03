package com.voufinal.event.mapper;

import com.voufinal.event.dto.EventBrandDto;
import com.voufinal.event.entity.EventBrand;

import org.springframework.stereotype.Service;

@Service
public class EventBrandMapper {

    // Convert EventBrand to EventBrandDto
    public static EventBrandDto toDto(EventBrand eventBrand) {
        if (eventBrand == null) {
            return null;
        }

        EventBrandDto eventBrandDto = new EventBrandDto();
        eventBrandDto.setEvent(EventMapper.toDto(eventBrand.getEvent()));
        eventBrandDto.setBrand_id(eventBrand.getBrand_id());

        return eventBrandDto;
    }

    // Convert EventBrandDto to EventBrand
    public static EventBrand toEntity(EventBrandDto dto) {
        if (dto == null) {
            return null;
        }

        EventBrand eventBrand = new EventBrand();
        eventBrand.setEvent(EventMapper.toEntity(dto.getEvent()));
        eventBrand.setBrand_id(dto.getBrand_id());

        return eventBrand;
    }
}