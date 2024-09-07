package com.voufinal.event_service.dto;

import com.voufinal.event_service.model.BrandsCooperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailDto {
    private Long idEvent;
    private String eventName;
    private int numberOfVouchers;
    private String imageUrl;
    private String brandLogo;
    private Long createdBy;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<BrandsCooperation> brandId;
    private GameInfoDto gameInfoDTO;
    private InventoryDetailDto inventoryInfo;
}
