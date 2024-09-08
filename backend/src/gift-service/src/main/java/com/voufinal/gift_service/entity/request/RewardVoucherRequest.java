package com.voufinal.gift_service.entity.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RewardVoucherRequest {
    List<Long> winnerIds;
    String voucherCode;
}
