package com.example.MegaCityCab_Booking_System.Driver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.Driver.entity.Driver;
import com.example.MegaCityCab_Booking_System.Driver.service.DriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("adddriver")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.addDriver(driver);
        return ResponseEntity.ok(savedDriver);
    }

    // Add methods to handle driver-related requests here
    @GetMapping("/getallDrivers")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/getdriverById")
    public Driver getDriverById(@PathVariable String id) {
        return driverService.getDriverbyid(id);
    }

    @PutMapping("updatedriver/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable String id, @RequestBody Driver driver) {
        Driver updatedDriver = driverService.updateDriver(id, driver);
        if (updatedDriver != null) {
            return ResponseEntity.ok(updatedDriver);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/deletedriver/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable String id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok("Driver deleted successfully");
    }
}
