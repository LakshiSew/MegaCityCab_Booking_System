package com.example.MegaCityCab_Booking_System.Booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Booking.entity.Booking;

@Service
public interface BookingService {
    
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(String id);
    Booking updateBooking(String id, Booking booking);
    boolean deleteBookingById(String id);


}
