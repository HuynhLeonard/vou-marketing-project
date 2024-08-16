package com.voufinal.brand_service.controller;

import com.voufinal.brand_service.dto.VoucherDTO;
import com.voufinal.brand_service.model.Voucher;
import com.voufinal.brand_service.service.VoucherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/voucher")
@RequiredArgsConstructor
@Tag(name= "Voucher Controller")
public class VoucherController {
    private final VoucherService voucherService;

    @GetMapping
    public List<Voucher> getAll() {
        return voucherService.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    public void createVoucher(@RequestParam Long brandId, @RequestPart(value = "voucherQR") MultipartFile voucherQR,
                                 @RequestPart(value = "voucherImage") MultipartFile voucherImage,
                                 @ModelAttribute VoucherDTO voucherDTO) {
        voucherService.addVoucher(brandId,voucherQR,voucherImage,voucherDTO);
    }
}
