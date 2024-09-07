package com.voufinal.userservice.controller;

import com.voufinal.userservice.common.InternalServerError;
import com.voufinal.userservice.common.NotFoundException;
import com.voufinal.userservice.common.NotFoundResponse;
import com.voufinal.userservice.common.SuccessResponse;
import com.voufinal.userservice.model.Brand;
import com.voufinal.userservice.service.BrandService;
import com.voufinal.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@CrossOrigin
public class BrandController {
    private final UserService userService;

    @Autowired
    private BrandService brandService;

    public BrandController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createBrand(@RequestBody Brand brand) {
        try {
            Brand savedBrand = userService.createBrand(brand);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllActiveBrands() {
        try {
            List<Brand> brands = brandService.getAllActiveBrands();
            if (brands.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Không tìm thấy thương hiệu nào đang hoạt động"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Lấy thành công danh sách các thương hiệu", HttpStatus.OK, brands));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new InternalServerError("Lỗi khi cố gắng lấy danh sách các thương hiệu đang hoạt động"));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBrandByUserId(@PathVariable Long userId) {
        try {
            Brand brand = userService.findBrandByUserId(userId);
            return ResponseEntity.ok(brand);
        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Không tìm thấy thương hiệu với id đã cho"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Lỗi khi lấy thương hiệu bằng id"));
        }
    }
}