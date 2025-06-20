package com.example.MegaCityCab_Booking_System.Category.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Category.entity.Category;

@Service
public interface CategoryService {
    
     Category createCategory(Category category);
     Category getCategoryById(String id);
     Category updateCategory(String id, Category category);
     void deleteCategoryById(String id);
     List<Category> getAllCategories();

    
}
