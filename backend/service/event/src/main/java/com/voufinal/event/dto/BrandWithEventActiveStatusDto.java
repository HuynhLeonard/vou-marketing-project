package com.voufinal.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import com.voufinal.event.common.EventIntermediateTableStatus;
import com.voufinal.event.common.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandWithEventActiveStatusDto {
    private String id;

    private String fullName;

    private String username;

    private String accountId;
    
    private String email;

    private String phone;

    private UserRole role;

    private String brandName;

    private String field;

    private String address;

    private double latitude;

    private double longitude;

    private boolean status;

    private EventIntermediateTableStatus active_status;
}
