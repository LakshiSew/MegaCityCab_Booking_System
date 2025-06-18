package com.example.MegaCityCab_Booking_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.model.Booking;

@Service
public interface BookingService {
    
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(String id);
    Booking updateBooking(String id, Booking booking);
    boolean deleteBookingById(String id);


}
