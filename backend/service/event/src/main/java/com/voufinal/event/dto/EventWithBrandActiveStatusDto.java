package com.voufinal.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.voufinal.event.common.EventIntermediateTableStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventWithBrandActiveStatusDto {
    private String id;
    
    private String name;

    private String image;

    private int numberOfVoucher;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private EventIntermediateTableStatus active_status;
}
