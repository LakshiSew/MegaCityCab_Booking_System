package com.example.MegaCityCab_Booking_System.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MegaCityCab_Booking_System.Customer.entity.Customer;
import com.example.MegaCityCab_Booking_System.Customer.repository.CustomerRepository;
import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;
import com.example.MegaCityCab_Booking_System.Cab.repository.CabsRepository;
import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;
import com.example.MegaCityCab_Booking_System.Admin.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CabsRepository cabRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUserName(username).orElse(null);
        if (customer != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(customer.getUserName())
                    .password(customer.getPassword())
                    .authorities("ROLE_CUSTOMER")
                    .build();
        }

        Cabs cabs = cabRepository.findByUserName(username).orElse(null);
        if (cabs != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(cabs.getUserName())
                    .password(cabs.getPassword())
                    .authorities("ROLE_DRIVER")
                    .build();
        }

        Admin admin = adminRepository.findByUsername(username).orElse(null);
        if (admin != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .authorities("ROLE_ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
