package com.voufinal.user_service.service;

import com.voufinal.user_service.model.User;
import com.voufinal.user_service.model.User_Role;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUserbyID(String id);

    List<User> getAllUsers();

    List<User> getUserByRole(User_Role role);

    User getUserbyAccountID(String accountId);

    User updateUser(User user);

    void deleteUserById(String id);
}
