package com.example.MegaCityCab_Booking_System.Driver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Driver.entity.Driver;

@Service
public interface DriverService {
     
   
    Driver addDriver(Driver driver);
    Driver updateDriver(String driverId, Driver driver);
    void deleteDriver(String driverId);
    Driver getDriverbyid(String driverId);
     List<Driver> getAllDrivers();
  


}
