package com.voufinal.user_service.service;

import com.voufinal.user_service.dto.LoginResponse;
import com.voufinal.user_service.dto.LoginUserResponse;
import com.voufinal.user_service.dto.UserResponse;
import com.voufinal.user_service.model.User;

import java.util.List;

public interface UserService {

    void addUser(UserResponse userResponse);

    List<User> getAllUsers();

    User getUserByID(Long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    LoginResponse loginUser(LoginUserResponse loginUserResponse);
}
