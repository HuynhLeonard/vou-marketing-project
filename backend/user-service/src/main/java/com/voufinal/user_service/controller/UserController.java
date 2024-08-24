package com.voufinal.user_service.controller;

import com.voufinal.user_service.dto.LoginResponse;
import com.voufinal.user_service.dto.LoginUserResponse;
import com.voufinal.user_service.dto.UserResponse;
import com.voufinal.user_service.model.User;
import com.voufinal.user_service.repository.UserRepository;
import com.voufinal.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserResponse userResponse){
        userService.addUser(userResponse);

        return "Create user succeed";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserResponse loginUserResponse){
        log.info(loginUserResponse.getEmail());
        LoginResponse loginMessage = userService.loginUser(loginUserResponse);
        return ResponseEntity.ok(loginMessage);
    }

    @GetMapping()
    public List<User> getAllUsers(){ return userService.getAllUsers(); }

    @GetMapping("/get")
    public User getUserById(@RequestParam Long id){
        return userService.getUserByID(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
