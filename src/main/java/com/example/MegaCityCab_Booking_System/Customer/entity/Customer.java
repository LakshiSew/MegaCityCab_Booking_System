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

    private String Id;

    private String customerName;

    private String customerEmail;

    private String userName;

    private String password;

    private String customerPhone;
    
}
