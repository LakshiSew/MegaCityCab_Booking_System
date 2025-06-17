package com.example.MegaCityCab_Booking_System.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.MegaCityCab_Booking_System.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    
}
