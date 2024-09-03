package com.voufinal.user_service.controller;


import com.voufinal.user_service.model.Brand;
import com.voufinal.user_service.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getall")
    public List<Brand> getAll() {
        return brandService.findAllBrands();
    }

    @GetMapping("/getbyId/{brandId}")
    public Brand getBrandById(@PathVariable String brandId) {
        return brandService.findBrandById(brandId);
    }

    @PostMapping("/addbrand")
    public Brand addBrand(@RequestBody Brand brand) {
        brand.setId("0");
        brandService.saveBrand(brand);
        return brand;
    }

    @PatchMapping("/update/{brandId}")
    public String updateBrand(@PathVariable String brandId, @RequestBody Brand brand) {
        brand.setId(brandId);
        brandService.updateBrand(brand);
        return "Update finish!";
    }

    @DeleteMapping("/delete/{brandId}")
    public String deleteBrand(@PathVariable("brandId") String brandId) {
        brandService.deleteBrandById(brandId);
        return "Delete finish!";
    }

    @GetMapping("/public/email/{email}")
    public Brand getBrandByEmail(@PathVariable String email) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        Brand brand = brandService.findBrandByEmail(email);
        if (brand == null) {
            throw new RuntimeException("No brand found by email");
        }
        return brand;
    }

    @GetMapping("/public/emails")
    public List<Brand> getBrandsByEmails(@RequestBody List<String> emails) {

        if (emails == null || emails.isEmpty()) {
            throw new RuntimeException("Emails are required");
        }
        List<Brand> brands = brandService.findManyBrandsByManyEmails(emails);
        if (brands == null || brands.isEmpty()) {
            throw new RuntimeException("No brand found by emails" );
        }
        return brands;
    }
}
