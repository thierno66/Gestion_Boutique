package com.thierno.gestion_boutique.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                System.out.println("AutEntryPoint called "+request);
            System.out.println("AutEntryPoint called");
        
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
            Map<String, Object> body = new HashMap<>();
            body.put("Status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("Error", "UNAUTHORIZED");
            body.put("message", authException.getMessage());
            body.put("Path", request.getServletPath());
        
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), body);
        
            System.out.println("Response sent: " + body);
    }

}
