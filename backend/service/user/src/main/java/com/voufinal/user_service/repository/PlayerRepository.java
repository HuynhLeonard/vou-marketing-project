package com.voufinal.user_service.repository;

import com.voufinal.user_service.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByEmail(String email);
    Player findByPhoneNumber(String phoneNumber);
}
