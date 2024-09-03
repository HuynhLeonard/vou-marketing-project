package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Admin;
import com.voufinal.user_service.repository.AdminRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(String adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);

        if (admin.isPresent()) {
            return admin.get();
        }
        else{
            throw new RuntimeException("Admin not found by id");
        }
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    @Transactional
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteAdminById(String adminId) {
        adminRepository.deleteById(adminId);
    }

}
