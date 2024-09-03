package com.voufinal.user_service.controller;

import com.voufinal.user_service.model.Customer;
import com.voufinal.user_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getalls")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/getbyId/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") String customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId("0");
        customerService.saveCustomer(customer);
        return customer;
    }

    @PatchMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return customer;
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") String customerId) {
        customerService.deleteCustomerById(customerId);
        return "Customer deleted";
    }

    @GetMapping("/phonenumber/{phoneNumber}")
    public Customer getCustomerByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return customerService.findCustomerByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.findCustomerByEmail(email);
    }
}
