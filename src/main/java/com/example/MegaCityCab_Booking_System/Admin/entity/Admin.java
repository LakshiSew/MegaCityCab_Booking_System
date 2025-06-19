package com.example.MegaCityCab_Booking_System.Admin.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "admins")
public class Admin {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String userId; // Link to the User who is an admin
    private String adminImage; // Optional field for profile picture URL
    


}
