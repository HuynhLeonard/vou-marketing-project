package com.voufinal.event_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestEvent {
    private String eventName;
    private Integer numberOfVouchers;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long createdBy;
}
