package com.example.MegaCityCab_Booking_System.Driver.repository;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MegaCityCab_Booking_System.Driver.entity.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver,String> {
    Optional<Driver> findByUserName(String userName);
}
