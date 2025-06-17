package com.example.MegaCityCab_Booking_System.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaCityCab_Booking_System.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    
}
