package com.voufinal.gift_service.entity.response;

import com.voufinal.gift_service.entity.UserVoucher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUserVoucherResponse {
    private List<UserVoucher> userVoucher;
}
