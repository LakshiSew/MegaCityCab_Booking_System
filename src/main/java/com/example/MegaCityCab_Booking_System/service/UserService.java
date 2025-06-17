package com.example.MegaCityCab_Booking_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.model.User;

@Service
public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id);  
    User updateUser(String id, User user);
    boolean deleteUserById(String id);
}
