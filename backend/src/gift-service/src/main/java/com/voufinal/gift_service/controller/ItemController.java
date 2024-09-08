package com.voufinal.gift_service.controller;

import com.voufinal.gift_service.common.ApiResponse;
import com.voufinal.gift_service.common.NotFoundResponse;
import com.voufinal.gift_service.common.SuccessResponse;
import com.voufinal.gift_service.entity.request.CreateItemRequest;
import com.voufinal.gift_service.exception.InternalServerError;
import com.voufinal.gift_service.service.ItemService;
import com.voufinal.gift_service.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getItem() {
        List<Item> items = itemService.getAllItems();
        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundResponse("Item list not found"));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse("List of all items", HttpStatus.OK, items));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createItem(@RequestBody CreateItemRequest request) {
        try {
            int result = itemService.createItem(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponse("Item created successfully", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerError("Error creating item"));
        }
    }

    @GetMapping("/{id_item}")
    public ResponseEntity<ApiResponse> getItemById(@PathVariable Long id_item) {
        try {
            Item result = itemService.getItemById(id_item);
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new NotFoundResponse("Item not found by id"));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessResponse("Item found by id", HttpStatus.OK, result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerError("Error retrieving item by id"));
        }
    }

    @PutMapping("/{id_item}")
    public ResponseEntity<ApiResponse> updateItemById(@PathVariable Long id_item,
                                                      @RequestBody CreateItemRequest request) {
        try {
            Integer result = itemService.updateItemById(id_item, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessResponse("Item updated successfully by id", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerError(e.getMessage()));
        }
    }

    @DeleteMapping("/{id_item}")
    public ResponseEntity<ApiResponse> deleteItemById(@PathVariable Long id_item) {
        try {
            Integer result = itemService.deleteItemById(id_item);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InternalServerError(e.getMessage()));
        }
    }

    @GetMapping("/events/{id_event}")
    public ResponseEntity<ApiResponse> getItemsByEvent(@PathVariable Long id_event) {
        try {
            Set<Item> items = itemService.getListItemsOfEvent(id_event);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("The items needed for this event are", HttpStatus.OK, items));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(e.getMessage()));
        }
    }
}
