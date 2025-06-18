package com.example.MegaCityCab_Booking_System.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.model.Customer;
import com.example.MegaCityCab_Booking_System.repository.CustomerRepository;
import com.example.MegaCityCab_Booking_System.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {
    // Implement the methods from CustomerService interface here
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(existingCustomer);
        }
        return null;

    }

    @Override
    public boolean deleteCustomerById(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
