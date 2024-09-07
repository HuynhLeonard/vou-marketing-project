package com.voufinal.userservice.repository;

import com.voufinal.userservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByIdUser(Long idUser);
}
