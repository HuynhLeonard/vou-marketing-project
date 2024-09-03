package com.voufinal.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name= "brands")
@DiscriminatorValue("brand")
public class Brand extends User {

    @Column(name="nameBrand")
    private String nameBrand;

    @Column(name="field")
    private String field;

    @Column(name="address")
    private String address;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    public Brand(){

    }

    public Brand(String nameBrand, String field, String address, double latitude, double longitude) {
        this.nameBrand = nameBrand;
        this.field = field;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Brand(String userName, String fullName, String accountId, String email, String phoneNumber, User_Role role, boolean status, String nameBrand, String field, String address, double latitude, double longitude){
        super(userName, fullName, accountId, email, phoneNumber, role, status);
        this.nameBrand = nameBrand;
        this.field = field;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "nameBrand='" + nameBrand + '\'' +
                ", field='" + field + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
