package com.voufinal.userservice.repository;

import com.voufinal.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByUsername(String username);
    User findByIdUser(Long userId);
    User findUserByUsernameAndEmail(String username, String email);
}
