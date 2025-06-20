package com.example.MegaCityCab_Booking_System.Category.serviceImpl;

import com.example.MegaCityCab_Booking_System.Category.entity.Category;
import com.example.MegaCityCab_Booking_System.Category.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(String id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setCategoryPic(category.getCategoryPic());
            existingCategory.setPricePrekm(category.getPricePrekm());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public void deleteCategoryById(String id) {
        categoryRepository.deleteById(id);
    }
}
