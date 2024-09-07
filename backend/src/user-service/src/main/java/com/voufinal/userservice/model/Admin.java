package com.voufinal.userservice.model;

import com.voufinal.userservice.constant.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "admins")
@AllArgsConstructor
@Getter
@Setter
public class Admin extends User {

}