package com.example.MegaCityCab_Booking_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.model.Customer;
import com.example.MegaCityCab_Booking_System.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

// Define methods for customer-related operations here

    @PostMapping("/createCustomer")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
     Customer savedCustomer = customerService.createCustomer(customer);
     return ResponseEntity.ok(savedCustomer);
  }
    
@GetMapping("/getAllCustomers")
public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
}
  
  @PutMapping("UpdateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        Customer updateCustomer = customerService.updateCustomer(id, customer);
        if(updateCustomer != null){
            return ResponseEntity.ok(updateCustomer);
        }else{
            return ResponseEntity.status(404).body(null);
        }
    }



@GetMapping("/getCustomerById/{id}")
public Customer getCustomerById(@PathVariable String id){
    return customerService.getCustomerById(id);
}


@DeleteMapping("/deleteCustomerById/{id}")
public ResponseEntity<String> deleteCustomerById(@PathVariable String id){
    boolean isDeleted = customerService.deleteCustomerById(id);
    if(isDeleted){
        return ResponseEntity.ok("Customer deleted successfully");
    }else{
        return ResponseEntity.status(404).body("Customer not found");
    }
}


    
}
