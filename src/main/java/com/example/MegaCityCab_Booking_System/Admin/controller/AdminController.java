package com.example.MegaCityCab_Booking_System.Admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;
import com.example.MegaCityCab_Booking_System.Admin.service.AdminService;
import com.example.MegaCityCab_Booking_System.Cloudinary.CloudinaryService;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

   @Autowired
  private PasswordEncoder passwordEncoder;



    @Autowired
    private CloudinaryService cloudinaryService;
@PostMapping("/auth/createadmin")
public ResponseEntity<?> createAdmin(
    @RequestParam("userName") String userName,
    @RequestParam("password") String password,
    @RequestParam("adminEmail") String adminEmail,
    @RequestParam("adminImage") MultipartFile adminImage
) throws IOException {

    if (adminService.existsByUsername(userName)) {
        return ResponseEntity.status(409).body("Username already exists");
    }

    // Upload admin image to Cloudinary
    String adminImageUrl = cloudinaryService.uploadImage(adminImage);

    // Create and save admin details
    Admin admin = new Admin();
    admin.setUserName(userName);
    admin.setPassword(password); // plain password
    admin.setEmail(adminEmail);
    admin.setAdminImage(adminImageUrl);

         admin.setPassword(passwordEncoder.encode(admin.getPassword()));

    Admin savedAdmin = adminService.createAdmin(admin);

    return ResponseEntity.ok(savedAdmin);
}


    @GetMapping("/getAlladmins")
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/getadminbyid/{id}")
    public Optional<Admin> getAdminById(@PathVariable String id ){
        return adminService.getAdminById(id);
    }

    @DeleteMapping("/deleteadminById/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable String id) {
        if (adminService.getAdminById(id).isPresent()) {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok("Admin deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Admin not found");
        }
    }

 @PutMapping("/updateadmin/{id}")
    public ResponseEntity<?> updateAdmin(
            @PathVariable String id,
            @RequestParam("userName") String userName,
            @RequestParam("email") String email,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "adminImage", required = false) MultipartFile adminImage
    ) throws IOException {
        try {
            String adminImageUrl = adminImage != null ? cloudinaryService.uploadImage(adminImage) : null;

            Admin admin = new Admin();
            admin.setUserName(userName);
            admin.setEmail(email);
            admin.setPassword(password != null && !password.isEmpty() ? passwordEncoder.encode(password) : null);
            admin.setAdminImage(adminImageUrl);

            Admin updatedAdmin = adminService.updateAdmin(id, admin);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
