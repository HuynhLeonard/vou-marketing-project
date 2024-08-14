package com.voufinal.user_service.service;

import com.voufinal.user_service.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    User getUserByID(Long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
