package com.example.MegaCityCab_Booking_System.Admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;

@Service
public interface AdminService {
    // Define methods for admin operations here
    // For example:

    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(String id);
    Admin createAdmin(Admin admin);
    void deleteAdmin(String id);
    public boolean existsByUsername(String userName);
    Admin updateAdmin(String id, Admin admin);

}
