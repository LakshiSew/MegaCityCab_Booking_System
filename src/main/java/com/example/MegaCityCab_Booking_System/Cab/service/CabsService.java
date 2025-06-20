package com.example.MegaCityCab_Booking_System.Cab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;

@Service
public interface CabsService {

    Cabs createCab(Cabs cab);
    List<Cabs> getAllCabs();    
    Cabs getCabById(String id);
    Cabs updateCab(String id, Cabs cab);
    void delete(String id);

    
}
 