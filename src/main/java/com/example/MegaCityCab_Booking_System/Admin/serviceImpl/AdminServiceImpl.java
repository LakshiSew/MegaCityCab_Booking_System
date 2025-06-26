package com.example.MegaCityCab_Booking_System.Admin.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;
import com.example.MegaCityCab_Booking_System.Admin.repository.AdminRepository;
import com.example.MegaCityCab_Booking_System.Admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(String id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return adminRepository.existsByUserName(userName);
    }

    @Override
    public Admin updateAdmin(String id, Admin updatedAdmin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();

            // Update fields only if provided
            if (updatedAdmin.getUserName() != null && !updatedAdmin.getUserName().isEmpty()) {
                existingAdmin.setUserName(updatedAdmin.getUserName());
            }
            if (updatedAdmin.getEmail() != null && !updatedAdmin.getEmail().isEmpty()) {
                existingAdmin.setEmail(updatedAdmin.getEmail());
            }
            if (updatedAdmin.getPassword() != null) {
                existingAdmin.setPassword(updatedAdmin.getPassword()); // Already encoded by controller
            }
            if (updatedAdmin.getAdminImage() != null) {
                existingAdmin.setAdminImage(updatedAdmin.getAdminImage());
            }

            // Save and return updated admin
            return adminRepository.save(existingAdmin);
        } else {
            throw new RuntimeException("Admin with ID " + id + " not found.");
        }
    }
}