package com.voufinal.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="players")

@DiscriminatorValue("player")
public class Player extends User {
    @Column(name="gender")
    private String gender;

    @Column(name= "accountFacebook")
    private String accountFacebook;

    @Column(name="dayofBirth")
    private String dayofBirth;

    @Column(name="avatar")
    private String avatar;

    public Player(String userName, String fullName, String accountId, String email, String phoneNumber, User_Role role, boolean status, String gender, String accountFacebook, String dayofBirth, String avatar) {
        super(userName, fullName, accountId, email, phoneNumber, role, status);
        this.gender = gender;
        this.accountFacebook = accountFacebook;
        this.dayofBirth = dayofBirth;
        this.avatar = avatar;
    }

    public Player(String gender, String accountFacebook, String dayofBirth, String avatar) {
        this.gender = gender;
        this.accountFacebook = accountFacebook;
        this.dayofBirth = dayofBirth;
        this.avatar = avatar;
    }

    public Player() {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountFacebook() {
        return accountFacebook;
    }

    public void setAccountFacebook(String accountFacebook) {
        this.accountFacebook = accountFacebook;
    }

    public String getDayofBirth() {
        return dayofBirth;
    }

    public void setDayofBirth(String dayofBirth) {
        this.dayofBirth = dayofBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Player{" +
                "gender='" + gender + '\'' +
                ", accountFacebook='" + accountFacebook + '\'' +
                ", dayofBirth='" + dayofBirth + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
