package com.voufinal.brand_service.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String brandName;
    private String password;
    private String category;
    private String confirmation;
    private boolean isTrivia;
    private boolean isShaking;
}
