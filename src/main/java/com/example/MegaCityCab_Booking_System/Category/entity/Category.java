package com.example.MegaCityCab_Booking_System.Category.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "categories")
public class Category {
    
    @Id
   private String id;
   private String name;
   private String categoryPic;
   private String pricePrekm;


}
