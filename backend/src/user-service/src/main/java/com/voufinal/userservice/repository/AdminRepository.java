package com.voufinal.userservice.repository;

import com.voufinal.userservice.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByIdUser(Long idUser);
}
