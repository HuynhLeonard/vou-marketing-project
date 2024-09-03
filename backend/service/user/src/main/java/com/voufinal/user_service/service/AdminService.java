package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();

    Admin getAdminById(String adminId);

    void saveAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdminById(String adminId);
}
