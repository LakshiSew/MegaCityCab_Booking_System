package com.example.MegaCityCab_Booking_System.Customer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String userId; // Link to the User who is a customer
    private String profilePictureUrl; // Optional field for profile picture URL
  
    
}
