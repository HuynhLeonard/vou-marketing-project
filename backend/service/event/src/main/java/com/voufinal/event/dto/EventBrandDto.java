package com.voufinal.event.dto;

import com.voufinal.event.common.EventIntermediateTableStatus;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventBrandDto {
    private EventDto event;
    
    private String brand_id;

    private EventIntermediateTableStatus activeStatus;
}