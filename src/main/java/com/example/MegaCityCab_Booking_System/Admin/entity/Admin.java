package com.example.MegaCityCab_Booking_System.Admin.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "admins")
public class Admin {
    private String id;
    private String userName;
    private String email;
    private String password;
    private String adminImage; // Optional field for profile picture URL
    


}
