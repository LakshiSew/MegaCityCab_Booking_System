package com.example.MegaCityCab_Booking_System.Booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.Booking.entity.Booking;
import com.example.MegaCityCab_Booking_System.Booking.service.BookingService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin
public class BookingController {
    
  @Autowired
  private BookingService bookingService;

@PostMapping("/createbooking")
public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
    Booking savedBooking = bookingService.createBooking(booking);
    return ResponseEntity.ok(savedBooking);
}


  
@GetMapping("/getallBookings")
public List<Booking> getAllBooklings(){
    return bookingService.getAllBookings();
}

@PutMapping("/updatebooking/{id}")
public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody Booking booking){
    Booking updateBooking = bookingService.updateBooking(id, booking);
    if(updateBooking != null){
        return ResponseEntity.ok(updateBooking);
    }else{
        return ResponseEntity.status(404).body(null);
    }
}

@GetMapping("/getbookingById/{id}")
public Booking getBookingById(@PathVariable String id){
    return bookingService.getBookingById(id);
}


@DeleteMapping("/deletebookingById/{id}")
public ResponseEntity<String> deleteBookingById(@PathVariable String id){
    boolean isDeleted = bookingService.deleteBookingById(id);
    if(isDeleted){
        return ResponseEntity.ok("Booking deleted successfully");
    }else{
        return ResponseEntity.status(404).body("Booking not found");
    }


}
}








