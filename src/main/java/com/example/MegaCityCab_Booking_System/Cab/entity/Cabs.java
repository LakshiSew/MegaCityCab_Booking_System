package com.example.MegaCityCab_Booking_System.Cab.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="cabs")
public class Cabs {
    

 @Id
    private String cabId;
    private String cabModel;
    private String numberPlate;
    private String categoryId;
    private String ownerName;
    private String ownerEmail;
    private String companyStatus;
    private String availabilityStatus;
    private String licenseImage;
    private String driverImage;
    private String cabLocation;
    private String imgUrl;
    private String userName;
    private String password;
    private Boolean ownDrive;
    private String contactNumber;
    private Double cabLatitude;
    private Double cabLongitude;


}
