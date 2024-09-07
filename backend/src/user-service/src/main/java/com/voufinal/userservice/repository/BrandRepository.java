package com.voufinal.userservice.repository;

import com.voufinal.userservice.constant.Status;
import com.voufinal.userservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByIdUser(Long userId);

    List<Brand> findBrandByStatusIs(Status status);
}
