package com.example.MegaCityCab_Booking_System.Authentication;

import com.example.MegaCityCab_Booking_System.Dto.LoginDto;
import com.example.MegaCityCab_Booking_System.Dto.LoginResponse;
// import com.example.MegaCityCab_Booking_System.DTO.LoginDTO;
// import com.example.MegaCityCab_Booking_System.DTO.LoginResponse;
// import com.example.MegaCityCab_Booking_System.Security.JWT.JWTUtils;
import com.example.MegaCityCab_Booking_System.Security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/api/auth") // Recommended to prefix for clarity
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginDto loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserName(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        String userId = jwtUtils.getUserIdFromJwtToken(token); // OPTIONAL: Only if your token stores userId

        return new LoginResponse(token, role, userId); // This assumes your LoginResponse has all 3 fields
    }
}
