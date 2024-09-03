package com.voufinal.user_service.controller;

import com.voufinal.user_service.model.Admin;
import com.voufinal.user_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService1) {
        adminService = adminService1;
    }

    @GetMapping()
    public List<Admin> findAll() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{adminId}")
    public Admin getAdminById(@PathVariable String adminId) {
        return adminService.getAdminById(adminId);
    }

    @PostMapping("/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin) {
        admin.setId("0");
        adminService.saveAdmin(admin);
        return admin;
    }

    @PatchMapping("/update")
    public Admin updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return admin;
    }

    @DeleteMapping("/delete/{adminId}")
    public String deleteAdmin(@PathVariable String adminId) {
        adminService.deleteAdminById(adminId);
        return "Deleted finish!";
    }   
}
