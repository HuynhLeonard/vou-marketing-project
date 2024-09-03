package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Brand;
import com.voufinal.user_service.repository.BrandRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findBrandById(String brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);

        if (brand.isPresent()) {
            return brand.get();
        }else{
            throw new RuntimeException("Brand not found by id");
        }
    }

    @Override
    @Transactional
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void updateBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(String brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public Brand findBrandByEmail(String email) {
        return brandRepository.findByEmail(email);
    }

    @Override
    public List<Brand> findManyBrandsByManyEmails(List<String> emails) {
        return brandRepository.findManyBrandsByManyEmails(emails);
    }


}
