package com.voufinal.brand_service.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EventDTO {
    private String eventName;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    private boolean isTriviaQuest;
    private boolean isShaking;  // for game using phone
}
