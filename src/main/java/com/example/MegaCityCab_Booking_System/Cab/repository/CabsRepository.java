package com.example.MegaCityCab_Booking_System.Cab.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;

public interface CabsRepository extends MongoRepository<Cabs, String> {
    Optional<Cabs> findByUserName(String userName);
}
