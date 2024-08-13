package com.voufinal.brand_service.service;

import com.voufinal.brand_service.model.Brand;
import com.voufinal.brand_service.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private RestTemplate restTemplate;

    // create game later with game-service

    public Optional<Brand> findByBrandName(String name) {
        return brandRepository.findBrandByBrandname(name);
    }

    public boolean existByName(String name) {
        return brandRepository.existsByBrandname(name);
    }

    public Long getBrandID(String name) throws Exception {
        Brand brand = brandRepository.findBrandByBrandname(name).orElseThrow(() -> new Exception("Username not found."));
        return brand.getId();
    }

    public List<Brand> getAllBrand(){
        return brandRepository.findAll();
    }

    // game service here
}
