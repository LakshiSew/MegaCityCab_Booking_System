package com.example.MegaCityCab_Booking_System.Cab.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;
import com.example.MegaCityCab_Booking_System.Cab.service.CabsService;
import com.example.MegaCityCab_Booking_System.Cloudinary.CloudinaryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
public class CabsController {
    

@Autowired
private CabsService cabsService;

    @Autowired
    private CloudinaryService cloudinaryService;

@GetMapping("/auth/getallCabs")
public List<Cabs> getAllCabs(){
    return cabsService.getAllCabs();
}


    @PostMapping("/auth/registercab")
    public ResponseEntity<Cabs> createCab(
        @RequestParam("imageUrl") MultipartFile file,
        @RequestParam("cabModel") String cabModel,
        @RequestParam("numberPlate") String nmberPlate,
        @RequestParam("companyStatus") String companyStatus,
        @RequestParam("availabilityStatus") String availabilityStatus,
        @RequestParam("ownDrive") Boolean ownDrive,
        @RequestParam("ownerName") String ownerName,
        @RequestParam("ownerEmail") String ownerEmail,
        @RequestParam("password") String password,
        @RequestParam(value = "licenseImage", required = false) MultipartFile licensefile, // optional
        @RequestParam(value = "driverImage", required = false) MultipartFile driverfile,   // optional
        @RequestParam("contactNumber") String contactNumber,
        @RequestParam("userName") String userName,
        @RequestParam("categoryId") String categoryId,
        @RequestParam("cabLocation") String cabLocation,
        @RequestParam("cabLatitude") String cabLatitude, 
        @RequestParam("cabLongitude") String cabLongitude 
    ) throws IOException {

        // Upload main image
        String imageUrl = cloudinaryService.uploadImage(file);

        // Initialize license and driver image URLs with defaults
        String licenseImage = "No driver";
        String driverImage = "No driver";

        // Upload license and driver images if ownDrive is true and files provided
        if (ownDrive) {
            if (licensefile != null && !licensefile.isEmpty()) {
                licenseImage = cloudinaryService.uploadImage(licensefile);
            }
            if (driverfile != null && !driverfile.isEmpty()) {
                driverImage = cloudinaryService.uploadImage(driverfile);
            }
        }

        // Create cab entity and set fields
        Cabs cab = new Cabs();
        cab.setCabModel(cabModel);
        cab.setNumberPlate(nmberPlate);  
        cab.setCompanyStatus(companyStatus);
        cab.setImgUrl(imageUrl);
        cab.setAvailabilityStatus(availabilityStatus);
        cab.setOwnDrive(ownDrive);
        cab.setOwnerName(ownerName);
        cab.setOwnerEmail(ownerEmail);
        cab.setPassword(password);
        cab.setLicenseImage(licenseImage);
        cab.setDriverImage(driverImage);
        cab.setContactNumber(contactNumber);
        cab.setUserName(userName);
        cab.setCategoryId(categoryId);
        cab.setCabLocation(cabLocation);
        cab.setCabLatitude(Double.parseDouble(cabLatitude));  
        cab.setCabLongitude(Double.parseDouble(cabLongitude));

        // Save and return response entity
        Cabs savedCab = cabsService.createCab(cab);
        return ResponseEntity.ok(savedCab);
    }




@GetMapping("/getcabById")
public Cabs getCabsById(@PathVariable String id){
    return cabsService.getCabById(id);
}


    @PostMapping("/updatecab/{id}")
    public ResponseEntity<Cabs> updateCab(@PathVariable String id, @RequestParam Cabs cabs) {
        Cabs updatedCabs = cabsService.updateCab(id, cabs);
        if (updatedCabs != null) {
            return ResponseEntity.ok(updatedCabs);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


    @DeleteMapping("/deletecabById/{id}")
    public ResponseEntity<String> deleteCabById(@PathVariable String id) {
        if (cabsService.getCabById(id) != null) {
            cabsService.delete(id);
            return ResponseEntity.ok("Cab deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Cab not found");
        }
    }
}


  