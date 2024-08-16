package com.voufinal.brand_service.service;

import com.voufinal.brand_service.dto.RegisterDTO;
import com.voufinal.brand_service.model.Brand;
import com.voufinal.brand_service.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    PasswordEncoder passwordEncoder;
    RestTemplate restTemplate;

    // create game later with game-service

    // register new brand
    public void register(RegisterDTO registerDTO){
        String name = registerDTO.getBrandName();
        String password = registerDTO.getPassword();
        String category = registerDTO.getCategory();
        boolean isShaking = registerDTO.isShaking();
        boolean isTrivia = registerDTO.isTrivia();

        passwordEncoder = new BCryptPasswordEncoder();
        Brand brand = new Brand();
        brand.setBrandname(name);
        brand.setPassword(passwordEncoder.encode(password));
        brand.setCategory(category);
        brand.setShaking(isShaking);
        brand.setTriviaQuest(isTrivia);

        brandRepository.save(brand);
    }

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

    // game service here (4 services here)
}
