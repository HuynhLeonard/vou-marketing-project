package com.voufinal.user_service.service;

import com.voufinal.user_service.dto.LoginResponse;
import com.voufinal.user_service.dto.LoginUserResponse;
import com.voufinal.user_service.dto.UserResponse;
import com.voufinal.user_service.model.User;
import com.voufinal.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIpml implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserResponse userResponse) {
        User user = new User(
                userResponse.getId(),
                userResponse.getUserName(),
                userResponse.getUserFullName(),
                this.passwordEncoder.encode(userResponse.getPassword()),
                userResponse.getEmail(),
                userResponse.getPhoneNumber(),
                userResponse.getStatus()
        );
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Long id) {
        User user =userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + id));
        return user;
    }

    @Override
    public void updateUser(Long id, User user) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + id));

        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user =userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + id));

        userRepository.delete(user);
    }

    @Override
    public LoginResponse loginUser(LoginUserResponse loginUserResponse) {

        User user = userRepository.findByEmail(loginUserResponse.getEmail());
        if (user != null) {
            String password = loginUserResponse.getPassword();
            String encodedPassword = user.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> userOptional = userRepository.findOneByEmailAndPassword(loginUserResponse.getEmail(), encodedPassword);
                if (userOptional.isPresent()) {
                    return new LoginResponse("Login Success", true);
                }
                else {
                    return new LoginResponse("Login Failed", false);
                }
            }
            else {
                return new LoginResponse("Login Failed", false);
            }
        }
        else {
            return new LoginResponse("Login Failed", false);
        }
    }


}
