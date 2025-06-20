package com.example.MegaCityCab_Booking_System.Category.repository;


import com.example.MegaCityCab_Booking_System.Category.entity.Category;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{
    
}
