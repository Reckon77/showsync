package com.ticket.booking.showsync.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.booking.showsync.dto.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        PrintWriter writer = response.getWriter();
//        writer.println("Access Denied !! " + authException.getMessage());
        response.setContentType("application/json");

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .error(authException.getMessage())
                .code(HttpStatus.UNAUTHORIZED.value())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
