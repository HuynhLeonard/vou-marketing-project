package com.voufinal.game.dto;

import lombok.Data;

@Data
public class GameDto {
    private Long id;
    private String name;
    private String type;
    private String image;
    private Boolean itemSwap;
    private String instruction;
}
