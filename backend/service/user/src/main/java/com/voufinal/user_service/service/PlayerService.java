package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomer();

    Customer findCustomerById(String customerId);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomerById(String customerId);

    Customer findCustomerByPhoneNumber(String phoneNumber);

    Customer findCustomerByEmail(String email);
}
