package com.voufinal.user_service.repository;

import com.voufinal.user_service.model.User;
import com.voufinal.user_service.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByRole(User_Role role);
    Optional<User> findByAccountId(String accountId);
}
