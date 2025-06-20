package com.example.MegaCityCab_Booking_System.Cab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;
import com.example.MegaCityCab_Booking_System.Cab.repository.CabsRepository;
import com.example.MegaCityCab_Booking_System.Cab.service.CabsService;


@Service
public class CabsServiceImpl implements CabsService {


    @Autowired
    private CabsRepository cabsRepository;

    @Override
    public Cabs createCab(Cabs cab) {
        return cabsRepository.save(cab);
    }

  @Override
    public java.util.List<Cabs> getAllCabs() {
        return cabsRepository.findAll();
    }

    @Override 
    public Cabs getCabById(String id) {
        return cabsRepository.findById(id).orElse(null);
    }

    @Override
    public Cabs updateCab(String id, Cabs cab) {
        Cabs existingCab = cabsRepository.findById(id).orElse(null);
        if (existingCab != null) {
            existingCab.setCabModel(cab.getCabModel());
            existingCab.setNumberPlate(cab.getNumberPlate());
            existingCab.setOwnerName(cab.getOwnerName()); // if ownerName is used as driver name
            return cabsRepository.save(existingCab);
        }
        return null;
    }


    @Override
    public void delete(String id) {
        cabsRepository.deleteById(id);
    }

    
}
