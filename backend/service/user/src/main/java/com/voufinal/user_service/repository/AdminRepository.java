package com.voufinal.user_service.repository;

import com.voufinal.user_service.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
