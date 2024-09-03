package com.voufinal.game.service;

import com.voufinal.game.dto.GameDto;
import com.voufinal.game.entity.Game;
import com.voufinal.game.exception.NotFoundException;
import com.voufinal.game.mapper.GameMapper;
import com.voufinal.game.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public List<GameDto> getAllGames() {
        List<Game> games = gameRepository.findAll();

        // a way to convert fast
        return games.stream()
                .map(GameMapper::toGameDto)
                .collect(Collectors.toList());
    }

    public GameDto getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Game", "id", id.toString())
        );

        return GameMapper.toGameDto(game);
    }

    public void createGame(GameDto gameDto) {
        Game game = GameMapper.toGame(gameDto);
        // later set auto id
        game.setId(null);
        gameRepository.save(game);
    }

    public void updateGame(GameDto gameDto) {
        // check game exist
        gameRepository.findById(gameDto.getId()).orElseThrow(
                () -> new NotFoundException("Game", "id", gameDto.getId().toString())
        );

        Game game = GameMapper.toGame(gameDto);
        gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Game", "id", id.toString())
        );

        gameRepository.delete(game);
    }
}
