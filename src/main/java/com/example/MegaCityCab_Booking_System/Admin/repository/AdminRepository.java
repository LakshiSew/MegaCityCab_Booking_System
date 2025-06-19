package com.example.MegaCityCab_Booking_System.Admin.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;


@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
        Optional<Admin> findByUserName(String userName);
    boolean existsByUserName(String userName);
    
    


}
