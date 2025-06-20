package com.example.MegaCityCab_Booking_System.Admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;
import com.example.MegaCityCab_Booking_System.Admin.service.AdminService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin
public class AdminController {
    
@Autowired
private AdminService adminService;


@PostMapping("/createadmin")
public ResponseEntity<String> createAdmin(@RequestBody Admin admin){
    if (adminService.existsByUsername(admin.getUsername())) {
        return ResponseEntity.status(409).body("Username already exists");
    }
    
    Admin savedAdmin = adminService.createAdmin(admin);
    return ResponseEntity.ok("Admin created successfully with ID: " + savedAdmin.getId());

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

@PutMapping("updateadmin/{id}")
public ResponseEntity<Admin> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
  Admin updatedAdmin = adminService.updateAdmin(id, admin);
    if (updatedAdmin != null) {
        return ResponseEntity.ok(updatedAdmin);
    } else {
        return ResponseEntity.status(404).body(null);
    }
}


}
