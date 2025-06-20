package com.example.MegaCityCab_Booking_System.Security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.MegaCityCab_Booking_System.Admin.entity.Admin;
import com.example.MegaCityCab_Booking_System.Admin.repository.AdminRepository;
import com.example.MegaCityCab_Booking_System.Cab.entity.Cabs;
import com.example.MegaCityCab_Booking_System.Cab.repository.CabsRepository;
import com.example.MegaCityCab_Booking_System.Customer.entity.Customer;
import com.example.MegaCityCab_Booking_System.Customer.repository.CustomerRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtUtils {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CabsRepository cabsRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Value("${app.secret}")
    private String secret;

    // Generate signing key from secret
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // Generate token
    public String generateJwtToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        String userId = null;

        if (role.equals("ROLE_CUSTOMER")) {
            userId = customerRepository.findByUserName(userDetails.getUsername())
                    .map(Customer::getId).orElse(null);
        } else if (role.equals("ROLE_DRIVER")) {
            userId = cabsRepository.findByUserName(userDetails.getUsername())
                    .map(Cabs::getCabId).orElse(null);
        } else if (role.equals("ROLE_ADMIN")) {
            userId = adminRepository.findByUsername(userDetails.getUsername())
                    .map(Admin::getId).orElse(null); // Assuming your Admin entity uses "id"
        }

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h expiry
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Extract username
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Extract userId from token claims
    public String getUserIdFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJws(token).getBody()
                .get("userId", String.class);
    }
}
