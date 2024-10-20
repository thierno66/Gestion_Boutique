package com.thierno.gestion_boutique.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UtilisateurService utilisateurService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       try {
        String token=jwtUtils.getTokenFromHeader(request);
        System.out.println(token);
       if (token != null && jwtUtils.validateToken(token)) {
        String username = (String) jwtUtils.getUsernameFromToken(token);
        UserDetails userDetails= utilisateurService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationtToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationtToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationtToken);
       }
       } catch (Exception e) {
        System.out.println("Erreur lors de l'authentification");
       }
       doFilter(request, response, filterChain);
    }

}
