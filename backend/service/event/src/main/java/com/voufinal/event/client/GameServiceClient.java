package com.voufinal.event.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.voufinal.event.dto.GameDto;

@FeignClient(name = "games-service", url = "http://games:8082/games")
public interface GameServiceClient {

    @GetMapping("/games/{id}")
    GameDto getGameById(@PathVariable("id") Long id);
}
