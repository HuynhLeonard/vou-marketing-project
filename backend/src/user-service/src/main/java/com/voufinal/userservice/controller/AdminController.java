package com.voufinal.userservice.controller;

import com.voufinal.userservice.common.*;
import com.voufinal.userservice.model.Admin;
import com.voufinal.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
@CrossOrigin
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createAdmin(@RequestBody Admin admin) {
        try {
            Admin savedAdmin = userService.createAdmin(admin);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAdminByUserId(@PathVariable Long userId) {
        try {
            Admin admin = userService.findAdminByUserId(userId);
            return ResponseEntity.ok(admin);
        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse("Not found admin"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError("Internal server error: error retrieving admin"));
        }
    }
}
