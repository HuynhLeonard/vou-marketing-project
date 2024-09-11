package com.voufinal.gameservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayerResultRequest {
    List<PlayerResult> list;

    public PlayerResultRequest(List<PlayerResult> rawRequest) {
        this.list = rawRequest;
    }
}