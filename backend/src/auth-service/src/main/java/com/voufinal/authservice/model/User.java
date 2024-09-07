package com.voufinal.authservice.model;
import com.voufinal.authservice.constant.Role;
import com.voufinal.authservice.constant.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private Long idUser;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDateTime lockedDate;
    private Role role;
    private Status status;
    private String address;
    private String avatarUrl;

    public User() {

    }

    public User(String username, String password, String fullName, String email, String phoneNumber, Role role, Status status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
    }

    public User(User user) {
        this.idUser = user.getIdUser();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
        this.status = user.getStatus();
    }
}

