package com.voufinal.user_service.service;

import com.voufinal.user_service.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    User getUserByID(Integer id);

    void updateUser(Integer idUser, User user);

    void deleteUser(Integer idUser);
}
