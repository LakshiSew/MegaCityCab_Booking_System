package com.example.MegaCityCab_Booking_System.Driver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "drivers")
public class Driver {

    @Id
    private String id;
    private String name;    
    private String driverImage;
    private String userName;
    private String password;
    private String licenceImg;
    private String email;
    private String companyStatus; 
    private double availableStatus; 
    private String location;
    



}


