package com.voufinal.event_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventImageResponse {
    String bannerUrl;
    String qrImgUrl;
    String voucherImgUrl;
}
