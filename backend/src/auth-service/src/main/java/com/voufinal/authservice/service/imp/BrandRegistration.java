package com.voufinal.authservice.service.imp;

import com.voufinal.authservice.model.Brand;
import com.voufinal.authservice.model.User;
import com.voufinal.authservice.service.UserManagementClient;
import com.voufinal.authservice.service.registration_interface.IRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BrandRegistration implements IRegistration {
    @Autowired
    private UserManagementClient client;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public byte register(User user) {
        try {
            User existUser = client.getUserByUsernameAndEmail(user.getUsername(), user.getEmail()).orElse(null);
            if (existUser != null) {
                return 0;
            }
        } catch (Exception e) {
            return 2;
        }

        String password = passwordEncoder.encode(user.getPassword());
        Brand brand = new Brand(user, password);
        try {
            if (client.createBrand(brand)) {
                return 1;
            }
            return 2;
        } catch (Exception e) {
            System.err.println("Failed to create Brand: " + e.getMessage());
            return 2;
        }
    }

    @Override
    public boolean verifyOtp(String username, String otp) {
        return false;
    }

    @Override
    public String resendOtp(String username, String email) {
        return null;
    }
}
