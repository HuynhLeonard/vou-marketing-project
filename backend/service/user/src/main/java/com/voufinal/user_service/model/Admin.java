package com.voufinal.user_service.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="admins")
@DiscriminatorValue("admin")
public class Admin extends User {

    public Admin() {}
    public Admin(String userName, String fullName, String accountId, String email, String phoneNumber, User_Role role, boolean status) {
        super(userName, fullName, accountId, email, phoneNumber, role, status);
    }


}
