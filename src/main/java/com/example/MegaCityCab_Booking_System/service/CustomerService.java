package com.example.MegaCityCab_Booking_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.model.Customer;

@Service
public interface CustomerService {

    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(String id);
    Customer updateCustomer(String id, Customer customer);
    boolean deleteCustomerById(String id);

    
}
