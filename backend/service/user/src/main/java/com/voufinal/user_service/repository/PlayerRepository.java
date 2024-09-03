package com.voufinal.user_service.repository;

import com.voufinal.user_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmail(String email);
    Customer findByPhoneNumber(String phoneNumber);
}
