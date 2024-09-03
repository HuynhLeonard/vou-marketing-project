package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAllBrands();

    Brand findBrandById(String brandId);

    void saveBrand(Brand brand);

    void updateBrand(Brand brand);

    void deleteBrandById(String brandId);

    Brand findBrandByEmail(String email);

    List<Brand> findManyBrandsByManyEmails(List<String> emails);
}
