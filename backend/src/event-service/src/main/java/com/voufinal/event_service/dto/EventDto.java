package com.voufinal.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String eventName;
    private int numberOfVouchers;
    private Long createdBy;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<Long> brandId;
    private GameInfoDto gameInfoDTO;
    private InventoryDto inventoryInfo;
}
