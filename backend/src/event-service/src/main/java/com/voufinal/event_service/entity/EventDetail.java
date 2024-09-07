package com.voufinal.event_service.entity;

import com.voufinal.event_service.dto.GameInfoDto;
import com.voufinal.event_service.dto.InventoryDetailDto;
import com.voufinal.event_service.model.BrandsCooperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDetail {
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
    private String instruction;
}
