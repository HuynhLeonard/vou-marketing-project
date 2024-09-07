package com.voufinal.userservice.repository;

import com.voufinal.userservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByIdSession(String token);
}
