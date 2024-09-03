package com.voufinal.user_service.repository;

import com.voufinal.user_service.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, String> {
    Brand findByEmail(String email);

    @Query("SELECT b FROM Brand b WHERE b.email IN :emails")
    List<Brand> findManyBrandsByManyEmails(@Param("emails") List<String> emails);
}
