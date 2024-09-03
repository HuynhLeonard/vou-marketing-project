package com.voufinal.user_service.service;

import com.voufinal.user_service.exception.ApplicationException;
import com.voufinal.user_service.exception.ErrorMess;
import com.voufinal.user_service.model.User;
import com.voufinal.user_service.model.User_Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voufinal.user_service.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(User user) {
        if(user.getRole() == null) {
            throw new ApplicationException(ErrorMess.INVALID_ROLE);
        }
        userRepository.save(user);
    }

    @Override
    public User getUserbyID(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMess.USER_NOT_EXISTED));
    }

    @Override
    @PreAuthorize("hasRole('admin')")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            throw new ApplicationException(ErrorMess.USER_NOT_EXISTED);
        }
        return users;
    }

    @Override
    @PreAuthorize("hasRole('admin')")
    public List<User> getUserByRole(User_Role role) {
        List<User> users = userRepository.findAll().stream()
                .filter(user -> user.getRole() == role)
                .toList();
        if(users.isEmpty()) {
            throw new ApplicationException(ErrorMess.USER_NOT_EXISTED);
        }
        return users;
    }

    @Override
    public User getUserbyAccountID(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ApplicationException(ErrorMess.USER_NOT_EXISTED));
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        if(user.getId() == null || user.getId().isEmpty()) {
            throw new ApplicationException(ErrorMess.INVALID_ROLE);
        }
        if(!userRepository.existsById(user.getId())) {
            throw new ApplicationException(ErrorMess.USER_NOT_EXISTED);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        if(!userRepository.existsById(id)) {
            throw new ApplicationException(ErrorMess.USER_NOT_EXISTED);
        }
        userRepository.deleteById(id);
    }


}
