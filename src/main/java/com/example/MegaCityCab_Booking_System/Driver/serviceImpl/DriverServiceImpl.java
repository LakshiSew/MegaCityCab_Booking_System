package com.example.MegaCityCab_Booking_System.Driver.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Driver.entity.Driver;
import com.example.MegaCityCab_Booking_System.Driver.repository.DriverRepository;
import com.example.MegaCityCab_Booking_System.Driver.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver addDriver(Driver driver){
        return driverRepository.save(driver);
    }

 @Override
    public Driver updateDriver(String id, Driver updatedDriver) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);

        if (optionalDriver.isPresent()) {
            Driver existingDriver = optionalDriver.get();

            existingDriver.setName(updatedDriver.getName());
            existingDriver.setDriverImage(updatedDriver.getDriverImage());
            existingDriver.setUserName(updatedDriver.getUserName());
            existingDriver.setPassword(updatedDriver.getPassword());
            existingDriver.setLicenceImg(updatedDriver.getLicenceImg());
            existingDriver.setEmail(updatedDriver.getEmail());
            existingDriver.setCompanyStatus(updatedDriver.getCompanyStatus());
            existingDriver.setAvailableStatus(updatedDriver.getAvailableStatus());
            existingDriver.setLocation(updatedDriver.getLocation());

            return driverRepository.save(existingDriver);
        } else {
            throw new RuntimeException("Driver with ID " + id + " not found.");
        }
    }


            @Override
            public void deleteDriver(String driverId) {
                driverRepository.deleteById(driverId);

        }

        @Override
            public Driver getDriverbyid(String driverId) {
                return driverRepository.findById(driverId).orElse(null);
            }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

}
