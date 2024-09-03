package com.voufinal.user_service.service;

import com.voufinal.user_service.model.Customer;
import com.voufinal.user_service.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            return customer.get();
        }else{
            throw new RuntimeException("Customer not found by id");
        }
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
