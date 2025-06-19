package com.example.MegaCityCab_Booking_System.Customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaCityCab_Booking_System.Customer.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    
}
