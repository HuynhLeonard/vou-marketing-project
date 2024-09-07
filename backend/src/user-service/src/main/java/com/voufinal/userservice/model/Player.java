package com.voufinal.userservice.model;

import com.voufinal.userservice.constant.Gender;
import com.voufinal.userservice.constant.Role;
import com.voufinal.userservice.constant.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player extends User {
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "facebook_url")
    private String facebookUrl;
}
