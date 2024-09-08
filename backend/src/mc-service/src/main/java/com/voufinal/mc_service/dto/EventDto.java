package com.voufinal.mc_service.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDto {
    private Long idEvent;

    private String eventName;

    private String imageUrl;

    private Integer numberOfVouchers;

    private Timestamp startDate;

    private Timestamp endDate;

    private Timestamp deletedDate;

    private Long createdBy;
}
