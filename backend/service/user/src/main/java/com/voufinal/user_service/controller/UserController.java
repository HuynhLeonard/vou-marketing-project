package com.voufinal.user_service.controller;

import com.voufinal.user_service.exception.BadReqException;
import com.voufinal.user_service.exception.NotFoundException;
import com.voufinal.user_service.model.User;
import com.voufinal.user_service.model.User_Role;
import com.voufinal.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/all-users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        System.out.println(user.getRole());
        if(user.getRole() == null) {
            throw new BadReqException("Role is required");
        }
        user.setRole(User_Role.valueOf(user.getRole().name()));
        userService.saveUser(user);
        return "Create user succeed";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserbyID(@PathVariable String id) {
        User user = userService.getUserbyID(id);

        if(user == null) {
            throw new NotFoundException("User not found with id");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/byrole/{role}")
    public ResponseEntity<List<User>> getUserbyRole(@PathVariable User_Role role) {
        return ResponseEntity.ok(userService.getUserByRole(role));
    }

    @GetMapping("/byaccount/{accountId}")
    public ResponseEntity<User> getUserbyAccount(@PathVariable String accountId) {
        User user = userService.getUserbyAccountID(accountId);
        if(user == null) {
            throw new NotFoundException("User not found with accountId");
        }
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        if(userService.getUserbyID(id) == null) {
            throw new NotFoundException("User not found with id");
        }
        user.setId(id);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if(userService.getUserbyID(id) == null) {
            throw new NotFoundException("User not found with id");
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
