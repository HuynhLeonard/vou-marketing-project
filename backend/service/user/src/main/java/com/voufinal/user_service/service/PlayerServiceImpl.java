package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Player;
import com.voufinal.user_service.repository.PlayerRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public Player findPlayerById(String customerId) {
        Optional<Player> customer = playerRepository.findById(customerId);

        if (customer.isPresent()) {
            return customer.get();
        }else{
            throw new RuntimeException("Customer not found by id");
        }
    }

    @Override
    @Transactional
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    @Transactional
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    @Transactional
    public void deletePlayerById(String customerId) {
        playerRepository.deleteById(customerId);
    }

    @Override
    public Player findPlayerByPhoneNumber(String phoneNumber) {
        return playerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Player findPlayerByEmail(String email) {
        return playerRepository.findByEmail(email);
    }
}
