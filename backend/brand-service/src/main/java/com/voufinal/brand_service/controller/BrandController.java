package com.voufinal.brand_service.controller;

import com.voufinal.brand_service.dto.RegisterDTO;
import com.voufinal.brand_service.model.Brand;
import com.voufinal.brand_service.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
@Tag(name = "Brand Controller")
public class BrandController {
    private final BrandService brandService;

    //register
    @PostMapping("register")
    public ResponseEntity<String> createNewBrand(@RequestBody RegisterDTO registerDTO) {
        // check duplicate
        if(brandService.existByName(registerDTO.getBrandName())) {
            return new ResponseEntity<>("Brand name is already taken", HttpStatus.BAD_REQUEST);
        }
        brandService.register(registerDTO);
        return new ResponseEntity<>("Brand registered successfully!", HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Brand> findAll() {
        return brandService.getAllBrand();
    }

    // for game
}
