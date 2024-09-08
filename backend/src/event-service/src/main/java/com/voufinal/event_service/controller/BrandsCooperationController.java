package com.voufinal.event_service.controller;

import com.voufinal.event_service.common.NotFoundResponse;
import com.voufinal.event_service.common.SuccessResponse;
import com.voufinal.event_service.common.CreatedResponse;
import com.voufinal.event_service.entity.CreateRequestBrandsCooperation;
import com.voufinal.event_service.exception.BadRequestException;
import com.voufinal.event_service.exception.InternalServerError;
import com.voufinal.event_service.model.BrandsCooperation;
import com.voufinal.event_service.service.BrandsCooperationService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/brands-cooperation")
public class BrandsCooperationController {
    @Autowired
    BrandsCooperationService brandsCooperationService;

    @GetMapping("")
    public ResponseEntity<?> getAllBrandsCooperation() {
        try {
            List<BrandsCooperation> brandsCooperations = brandsCooperationService.getAllBrandsCooperation();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("List of brands cooperation", HttpStatus.OK, brandsCooperations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError());
        }
    }

    @GetMapping("/{id_event_id_brand}")
    public ResponseEntity<?> getBrandsCooperationById(@PathVariable("id_event_id_brand") Long id_event_id_brand) {
        try {
            BrandsCooperation brandsCooperation = brandsCooperationService.getBrandsCooperationById(id_event_id_brand);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Brand cooperation details", HttpStatus.OK, brandsCooperation));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Brand cooperation not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addBrandsCooperation(@RequestBody CreateRequestBrandsCooperation request) {
        try {
            BrandsCooperation createdBrandsCooperation = brandsCooperationService.createBrandsCooperation(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreatedResponse("Brand cooperation created", createdBrandsCooperation));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id_event_id_brand}")
    public ResponseEntity<?> updateBrandsCooperation(@PathVariable("id_event_id_brand") Long id, @RequestBody CreateRequestBrandsCooperation request) {
        try {
            BrandsCooperation updatedBrandsCooperation = brandsCooperationService.updateBrandsCooperation(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Brand cooperation updated", HttpStatus.OK, updatedBrandsCooperation));
        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Brand cooperation not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError());
        }
    }

    @DeleteMapping("/{id_event_id_brand}")
    public ResponseEntity<?> deleteBrandsCooperation(@PathVariable("id_event_id_brand") Long id) {
        try {
            boolean result = brandsCooperationService.deleteBrandsCooperationById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Brand cooperation deleted", HttpStatus.OK, null));
        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Brand cooperation not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError());
        }
    }
}