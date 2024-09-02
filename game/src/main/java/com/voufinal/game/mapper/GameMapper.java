package com.voufinal.game.mapper;

import com.voufinal.game.dto.GameDto;
import com.voufinal.game.entity.Game;
public class GameMapper {
    public static Game toGame(GameDto gameDto){
        Game res = new Game();
        res.setId(gameDto.getId());
        res.setName(gameDto.getName());
        res.setImage(gameDto.getImage());
        res.setType(gameDto.getType());
        res.setInstruction(gameDto.getInstruction());
        res.setItemSwap(gameDto.getItemSwap());
        return res;
    }

    public static GameDto toGameDto(Game game) {
        GameDto res = new GameDto();
        res.setId(game.getId());
        res.setImage(game.getImage());
        res.setName(game.getName());
        res.setType(game.getType());
        res.setInstruction(game.getInstruction());
        res.setItemSwap(game.getItemSwap());
        return res;
    }
}
