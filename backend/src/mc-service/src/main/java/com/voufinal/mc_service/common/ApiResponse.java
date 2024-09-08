package com.voufinal.mc_service.common;

import org.springframework.http.HttpStatus;

public interface ApiResponse {
    public HttpStatus getStatus();
    public Object getMetadata();
    public String getMessage();
}
