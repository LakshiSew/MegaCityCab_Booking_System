package com.example.MegaCityCab_Booking_System.Customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.Customer.entity.Customer;
import com.example.MegaCityCab_Booking_System.Customer.service.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
public class CustomerController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    CustomerController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

// Define methods for customer-related operations here

    @PostMapping("/auth/createcustomer")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
     customer.setPassword(passwordEncoder.encode(customer.getPassword()));
     Customer savedCustomer = customerService.createCustomer(customer);
     return ResponseEntity.ok(savedCustomer);
  }
    
@GetMapping("/getallCustomers")
public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
}
  
  @PutMapping("Updatecustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        Customer updateCustomer = customerService.updateCustomer(id, customer);
        if(updateCustomer != null){
            return ResponseEntity.ok(updateCustomer);
        }else{
            return ResponseEntity.status(404).body(null);
        }
    }



@GetMapping("/getcustomerById/{id}")
public Customer getCustomerById(@PathVariable String id){
    return customerService.getCustomerById(id);
}


@DeleteMapping("/deletecustomerById/{id}")
public ResponseEntity<String> deleteCustomerById(@PathVariable String id){
    boolean isDeleted = customerService.deleteCustomerById(id);
    if(isDeleted){
        return ResponseEntity.ok("Customer deleted successfully");
    }else{
        return ResponseEntity.status(404).body("Customer not found");
    }
}


    
}
