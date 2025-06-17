package com.example.MegaCityCab_Booking_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.model.User;
import com.example.MegaCityCab_Booking_System.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
public class UserController {
    

 @Autowired
 private UserService userService;

@PostMapping("/createUser")
public ResponseEntity<User> createUser(@RequestBody User user) {

    User savedUser = userService.createUser(user);
    return ResponseEntity.ok(savedUser);
}

@GetMapping("/getUser")
public List<User> getAllUsers(){
    return userService.getAllUsers();
}
   

@GetMapping("/getUserById/{id}")
public User getUserById(@PathVariable String id){
    return userService.getUserById(id);
}

@PutMapping("/updateUser/{id}")
public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
    User updatedUser = userService.updateUser(id, user);
    if (updatedUser != null) {
        return ResponseEntity.ok(updatedUser);
    } else {
        return ResponseEntity.status(404).body(null);
    }
}


@DeleteMapping("/deleteUserById/{id}")
public ResponseEntity<String> deleteUserById(@PathVariable String id){
    boolean isDeleted = userService.deleteUserById(id);
    if(isDeleted){
        return ResponseEntity.ok("User deleted successfully");
    }else{
        return ResponseEntity.status(404).body("User not found");
    }
} 





}


