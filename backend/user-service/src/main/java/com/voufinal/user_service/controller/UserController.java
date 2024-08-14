package com.voufinal.user_service.controller;

import com.voufinal.user_service.model.User;
import com.voufinal.user_service.repository.UserRepository;
import com.voufinal.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody User user){
        userService.addUser(user);

        return "Create user succeed";
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
