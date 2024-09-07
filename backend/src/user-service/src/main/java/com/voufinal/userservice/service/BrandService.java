package com.voufinal.userservice.service;

import com.voufinal.userservice.constant.Status;
import com.voufinal.userservice.model.Brand;
import com.voufinal.userservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllActiveBrands() {
        return brandRepository.findBrandByStatusIs(Status.ACTIVE);
    }
}
