package com.voufinal.event_service.entity;

import com.voufinal.event_service.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminStatisticsResponse {
    private Long totalEvents;
    private Long totalVouchers;
    private List<Event> eventList;
}
