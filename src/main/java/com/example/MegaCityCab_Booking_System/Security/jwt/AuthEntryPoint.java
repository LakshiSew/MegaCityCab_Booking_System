package com.example.MegaCityCab_Booking_System.Security.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

/**
 * This class handles unauthorized access attempts.
 * If a user tries to access a protected endpoint without valid credentials, it sends a 401 Unauthorized error.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
                         throws IOException, ServletException {

        // Sends a 401 response when authentication fails
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized access");
    }
}
