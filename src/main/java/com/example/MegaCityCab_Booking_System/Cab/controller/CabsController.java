package com.example.MegaCityCab_Booking_System.Cab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;
import com.example.MegaCityCab_Booking_System.Cab.service.CabsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
public class CabsController {
    

@Autowired
private CabsService cabsService;

@GetMapping("/getallCabs")
public List<Cabs> getAllCabs(){
    return cabsService.getAllCabs();
}



@PostMapping("/createcab")
public ResponseEntity<Cabs> createCab(@RequestBody Cabs cabs){
    Cabs savedCabs = cabsService.createCab(cabs);
    return ResponseEntity.ok(savedCabs);
}




@GetMapping("/getcabById")
public Cabs getCabsById(@PathVariable String id){
    return cabsService.getCabById(id);
}


    @PostMapping("/updatecab/{id}")
    public ResponseEntity<Cabs> updateCab(@PathVariable String id, @RequestParam Cabs cabs) {
        Cabs updatedCabs = cabsService.updateCab(id, cabs);
        if (updatedCabs != null) {
            return ResponseEntity.ok(updatedCabs);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


    @DeleteMapping("/deletecabById/{id}")
    public ResponseEntity<String> deleteCabById(@PathVariable String id) {
        if (cabsService.getCabById(id) != null) {
            cabsService.delete(id);
            return ResponseEntity.ok("Cab deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Cab not found");
        }
    }
}


  