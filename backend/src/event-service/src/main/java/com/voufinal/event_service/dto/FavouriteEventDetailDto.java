package com.voufinal.event_service.dto;

import com.voufinal.event_service.model.Event;
import com.voufinal.event_service.model.FavouriteEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteEventDetailDto {
    Long idFavouriteEvent;
    Long idEvent;
    String eventBanner;
    String eventName;
    Date startDate;
    Date endDate;
    String brandLogo;
    Long idBrand;

    public FavouriteEventDetailDto(FavouriteEvent favouriteEvent, Event event, String brandLogo, Long idBrand) {
        this.idFavouriteEvent = favouriteEvent.getIdFavouriteEvent();
        this.idEvent = favouriteEvent.getIdEvent();
        this.eventBanner = event.getImageUrl();
        this.eventName = event.getEventName();
        this.startDate = favouriteEvent.getStartDate();
        this.endDate = favouriteEvent.getEndDate();
        this.brandLogo = brandLogo;
        this.idBrand = idBrand;
    }
}
