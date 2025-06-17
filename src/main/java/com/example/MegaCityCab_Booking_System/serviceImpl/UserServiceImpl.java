package com.example.MegaCityCab_Booking_System.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.model.User;
import com.example.MegaCityCab_Booking_System.repository.UserRepository;
import com.example.MegaCityCab_Booking_System.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
      return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }
        return null;
    }


        @Override
        public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
        }


    @Override
    public boolean deleteUserById(String id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }




}
