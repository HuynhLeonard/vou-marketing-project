package com.voufinal.user_service.service;

import com.voufinal.user_service.model.User;
import com.voufinal.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceIpml implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Integer id) {
        User user =userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + id));
        return user;
    }

    @Override
    public void updateUser(Integer idUser, User user) {
        userRepository
                .findById(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + idUser));

        user.setIdUser(idUser);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer idUser) {
        User user =userRepository
                .findById(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + idUser));

        userRepository.delete(user);
    }


}
