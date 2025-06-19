package com.example.MegaCityCab_Booking_System.Booking.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "bookings")
public class Booking {
  
    private String id;
    private String bookingNumber;
    private String customerId; // Link to the Customer who made the booking
    private String driverId; // Link to the Driver assigned to the booking
    private String cabId; // Link to the Cab used for the booking
    private String pickupLocation;
    private String dropoffLocation;
     private LocalDateTime bookingTime;// Timestamp of when the booking was made
    private String status; // e.g., "Pending", "Completed", "Cancelled"

}
