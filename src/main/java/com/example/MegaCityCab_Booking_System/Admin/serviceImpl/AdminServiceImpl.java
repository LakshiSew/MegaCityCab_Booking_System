package com.example.MegaCityCab_Booking_System.Admin.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;
import com.example.MegaCityCab_Booking_System.Admin.repository.AdminRepository;
import com.example.MegaCityCab_Booking_System.Admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService  {
    

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
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
    @Override
    public Admin updateAdmin(String id, Admin updatedAdmin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();

            // Update fields
            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setEmail(updatedAdmin.getEmail());
            existingAdmin.setPhoneNumber(updatedAdmin.getPhoneNumber());
            existingAdmin.setPassword(updatedAdmin.getPassword()); // Consider encrypting this if necessary
            existingAdmin.setUserId(updatedAdmin.getUserId());
            existingAdmin.setAdminImage(updatedAdmin.getAdminImage());

            // Save updated admin
            return adminRepository.save(existingAdmin);
        } else {
            throw new RuntimeException("Admin with ID " + id + " not found.");
        }
    }


}
