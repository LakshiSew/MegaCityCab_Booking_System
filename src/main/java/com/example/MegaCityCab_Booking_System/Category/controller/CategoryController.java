package com.example.MegaCityCab_Booking_System.Category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.Category.entity.Category;
import com.example.MegaCityCab_Booking_System.Category.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/createcategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping("getCategorybyId/{id}")
    public Category getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("updatecategory/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category category){
    Category updateCategory = categoryService.updateCategory(id, category);
    if(updateCategory != null){
        return ResponseEntity.ok(updateCategory);
    }else{
        return ResponseEntity.status(404).body(null);
    }
  }


@DeleteMapping("/deletecategoryById/{id}")
public ResponseEntity<String> deleteCategoryById(@PathVariable String id){
    categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @GetMapping("path")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }



}



