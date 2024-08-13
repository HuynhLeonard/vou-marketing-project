package com.voufinal.brand_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.voufinal.brand_service.model.Brand;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByBrandname(String name);
    Boolean existsByBrandname(String name);
}
