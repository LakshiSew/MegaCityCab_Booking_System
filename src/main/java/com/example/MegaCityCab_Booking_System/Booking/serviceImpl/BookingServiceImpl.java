package com.example.MegaCityCab_Booking_System.Booking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Booking.entity.Booking;
import com.example.MegaCityCab_Booking_System.Booking.repository.BookingRepository;
import com.example.MegaCityCab_Booking_System.Booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(String id){
        return bookingRepository.findById(id).orElse(null);
    }
@Override
public Booking updateBooking(String id, Booking booking) {
    Booking existingBooking = bookingRepository.findById(id).orElse(null);

    if (existingBooking != null) {
        existingBooking.setPickupLocation(booking.getPickupLocation());
        existingBooking.setDropoffLocation(booking.getDropoffLocation());
        existingBooking.setBookingTime(booking.getBookingTime());
        existingBooking.setStatus(booking.getStatus());
        existingBooking.setCabId(booking.getCabId());
        existingBooking.setDriverId(booking.getDriverId());
        existingBooking.setCustomerId(booking.getCustomerId());
        
        return bookingRepository.save(existingBooking);
    } else {
        return null; // or throw an exception if you want to handle not found
    }
}
@Override
public boolean deleteBookingById(String id) {
    if (bookingRepository.existsById(id)) {
        bookingRepository.deleteById(id);
        return true;
    }
    return false;

}
}
