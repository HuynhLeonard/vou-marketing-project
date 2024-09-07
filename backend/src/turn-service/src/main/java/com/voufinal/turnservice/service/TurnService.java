package com.voufinal.turnservice.service;

import com.voufinal.turnservice.common.*;
import com.voufinal.turnservice.entity.CreateTurnRequest;
import com.voufinal.turnservice.entity.Turn;
import com.voufinal.turnservice.repository.TurnRepository;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnService {

    private final TurnRepository turnRepository;

    @Autowired
    public TurnService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }

    public Object createTurn(CreateTurnRequest request) {
        Turn turn = new Turn();
        turn.setIdPlayer(request.getIdPlayer());
        turn.setIdGame(request.getIdGame());
        turn.setScore(request.getScore());
        turn.setTurns(request.getTurns());

        Turn savedTurn = turnRepository.save(turn);
        return savedTurn;
    }

    public Object getListTurns() {
        List<Turn> turns = turnRepository.findAll();
        return turns;
    }

    public Object getPlaySessionById(Long idPlaySession) {
        // Giả sử idPlaySession có thể tương ứng với Turn's id
        Optional<Turn> turn = turnRepository.findById(idPlaySession);
        return turn.orElse(null);
    }

    public Object getTurn(Long idPlayer, Long idGame) {
        Turn turn = turnRepository.findByIdPlayerAndIdGame(idPlayer, idGame);
        return turn;
    }

    public Object updatePlaySession(Long idPlaySession) {
        // Cập nhật logic cho PlaySession, giả sử là Turn trong trường hợp này
        Optional<Turn> existingTurn = turnRepository.findById(idPlaySession);
        if (existingTurn.isPresent()) {
            Turn turn = existingTurn.get();
            // Cập nhật các trường nếu cần
            return turnRepository.save(turn);
        }
        return null;
    }

    public Object updateTurn(Long idPlayer, Long idGame) {
        Turn turn = turnRepository.findByIdPlayerAndIdGame(idPlayer, idGame);
        if (turn != null) {
            // Cập nhật các trường nếu cần
            return turnRepository.save(turn);
        }
        return null;
    }

    public Object deletePlaySession(Long idPlaySession) {
        // Xóa Turn bằng idPlaySession
        if (turnRepository.existsById(idPlaySession)) {
            turnRepository.deleteById(idPlaySession);
            return new SuccessResponse("Play session deleted successfully", HttpStatus.OK, null);
        }
        return new NotFoundResponse("Play session not found");
    }

    public Object deleteTurn(Long idPlayer, Long idGame) {
        Turn turn = turnRepository.findByIdPlayerAndIdGame(idPlayer, idGame);
        if (turn != null) {
            turnRepository.delete(turn);
            return new SuccessResponse("Turn deleted successfully", HttpStatus.OK, null);
        }
        return new NotFoundResponse("Turn not found");
    }
}
