package com.voufinal.gift_service.controller;

import com.voufinal.gift_service.common.ApiResponse;
import com.voufinal.gift_service.common.SuccessResponse;
import com.voufinal.gift_service.entity.request.GiftItemRequest;
import com.voufinal.gift_service.exception.InternalServerError;
import com.voufinal.gift_service.service.GiftLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/gifts")
@CrossOrigin
public class GiftLogController {
    @Autowired
    private GiftLogService giftLogService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> giftAnItem(@RequestBody GiftItemRequest request) {
        try {
            giftLogService.giftAnItemForUser(request);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Successfully sent the item", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }

    @GetMapping("/users/{id_user}")
    public ResponseEntity<ApiResponse> getGiftingHistoryByUserId(@PathVariable Long id_user) {
        try {
            HashMap<String, Object> giftingHistory = giftLogService.getGiftingHistoryByUserId(id_user);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Successfully retrieved gifting history", HttpStatus.OK, giftingHistory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }
}
