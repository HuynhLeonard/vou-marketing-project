package com.voufinal.event_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateRequestEvent {
    private String eventName;
    private Integer numberOfVouchers;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long createdBy;


    public CreateRequestEvent(String eventName, Integer numberOfVouchers, Timestamp startDate, Timestamp endDate, Long createdBy) {
        this.eventName = eventName;
        this.numberOfVouchers = numberOfVouchers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }
}
