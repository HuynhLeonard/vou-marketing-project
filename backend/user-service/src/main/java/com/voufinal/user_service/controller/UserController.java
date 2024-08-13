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
    public User getUserById(@RequestParam Integer idUser){
        return userService.getUserByID(idUser);
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer idUser, @RequestBody User user){
        userService.updateUser(idUser, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer idUser){
        userService.deleteUser(idUser);

        return ResponseEntity.noContent().build();
    }
}
