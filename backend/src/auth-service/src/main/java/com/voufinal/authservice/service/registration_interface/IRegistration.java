package com.voufinal.authservice.service.registration_interface;

import com.voufinal.authservice.model.User;

public interface IRegistration {
    byte register(User user);
    boolean verifyOtp(String username, String otp);
    String resendOtp(String username, String email);
}
