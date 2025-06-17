package com.example.MegaCityCab_Booking_System.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
    private String password;
    
    // Role: either "admin" or "customer"
    private String role;

    // If role is "customer", link to Customer
    private String customerId;
 

}
