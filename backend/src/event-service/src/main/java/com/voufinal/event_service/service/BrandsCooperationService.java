package com.voufinal.event_service.service;

import com.voufinal.event_service.dto.BrandDto;
import com.voufinal.event_service.entity.CreateRequestBrandsCooperation;
import com.voufinal.event_service.model.BrandsCooperation;
import com.voufinal.event_service.repository.BrandsCooperationRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;

@Service
public class BrandsCooperationService {
    @Autowired
    private BrandsCooperationRepository brandsCooperationRepository;
    @Autowired
    private BrandClient brandClient;

    public List<BrandsCooperation> getAllBrandsCooperation() throws Exception {
        try {
            return brandsCooperationRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public BrandsCooperation getBrandsCooperationById(Long id) throws Exception {
        try {
            BrandsCooperation result = brandsCooperationRepository.findByIdBrandCooperation(id);
            if (result == null) {
                throw new NotActiveException("Brand cooperation not found");
            } else {
                return result;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean deleteBrandsCooperationById(Long id) throws Exception {
        try {
            BrandsCooperation result = brandsCooperationRepository.findByIdBrandCooperation(id);
            if (result == null) {
                throw new NotActiveException("Brand cooperation not found");
            } else {
                brandsCooperationRepository.delete(result);
                return true;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public BrandsCooperation updateBrandsCooperation(Long id, CreateRequestBrandsCooperation request) throws Exception {
        try {
            BrandsCooperation newBrandsCooperation = brandsCooperationRepository.findByIdBrandCooperation(id);
            if (newBrandsCooperation == null) {
                throw new NotFoundException("Brand cooperation not found");
            }
            Optional.ofNullable(request.getIdBrand()).ifPresent(action ->
                    newBrandsCooperation.setIdBrandCooperation(request.getIdBrand()));
            Optional.ofNullable(request.getIdEvent()).ifPresent(action ->
                    newBrandsCooperation.setIdEvent(request.getIdEvent()));

            brandsCooperationRepository.save(newBrandsCooperation);
            return newBrandsCooperation;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public BrandsCooperation createBrandsCooperation(CreateRequestBrandsCooperation request) throws Exception {
        BrandsCooperation newBrandsCooperation = new BrandsCooperation();
        if (request.getIdBrand() == null || request.getIdEvent() == null) {
            throw new Exception("Invalid input");
        }
        BrandDto brand = brandClient.getBrandByIdBrand(request.getIdBrand()).orElse(null);
        if (brand == null) {
            throw new Exception("Error when finding brand");
        }
        newBrandsCooperation.setIdBrand(request.getIdBrand());
        newBrandsCooperation.setIdEvent(request.getIdEvent());
        newBrandsCooperation.setNameBrand(brand.getFullName());
        try {
            brandsCooperationRepository.save(newBrandsCooperation);
            return newBrandsCooperation;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}