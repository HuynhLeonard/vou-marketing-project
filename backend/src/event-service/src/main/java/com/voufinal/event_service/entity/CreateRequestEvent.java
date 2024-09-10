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
        this.startDate = subtractSevenHours(startDate);
        this.endDate = subtractSevenHours(endDate);
        this.createdBy = createdBy;
    }
    public static Timestamp subtractSevenHours(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        LocalDateTime localDateTime = timestamp.toLocalDateTime().minusHours(7);

        return Timestamp.valueOf(localDateTime);
    }
}
