package com.thierno.gestion_boutique.config;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.thierno.gestion_boutique.entite.User;
import com.thierno.gestion_boutique.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
@Component
public class JwtUtils {
    @Autowired
    private UserRepository userRepository;
    @Value("${spring.app.jwtSecret}")
    private String JwtSecret;
    @Value("${spring.app.jwtExpiration}")
    private int jwtExpiration;
    public String getTokenFromHeader(HttpServletRequest request){
        String bearerToken= request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }

        public String generateTokenFromUsername(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Date creationDate = new Date();
        Long expiration=creationDate.getTime()+jwtExpiration;
        User user= userRepository.findByEmail(username);
        Map<String, Object> claims = new HashMap<>();
        claims.put("nom", user.getNom()); // Assurez-vous que UserDetails contient ces informations
        claims.put("prenom", user.getPrenom());
        claims.put("telephone", user.getTelephone());
        claims.put("roles", user.getRoles());
        claims.put("username", user.getEmail());
        claims.put("expiration", new Date(expiration));


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(creationDate)
                .setExpiration(new Date(expiration))
                .signWith(getKey())
                .compact();
    }

    // public String generateTokenFromUsername(UserDetails userDetails){
    //     String username= userDetails.getUsername();
    //     Date creationDate=new Date();
    //     Long expiration=creationDate.getTime()+jwtExpiration;
    //     User user= userRepository.findByEmail(username);
    //     Map<String,Object> claims=new HashMap<>();
    //     claims.put("nom", user.getNom());
    //     claims.put("prenom", user.getPrenom());
    //     claims.put("telephone", user.getTelephone());
    //     claims.put("roles", user.getRoles());
    //     claims.put("username", user.getEmail());
    //     claims.put("expiration", new Date(expiration));
    //     return Jwts.builder()
    //               .setIssuedAt(creationDate)
    //               .setSubject(username)
    //               .setExpiration(new Date(expiration))
    //               .setClaims(claims)
    //               .signWith(getKey())
    //               .compact();

    // }
    // public SecretKey getKey(){
    //     return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtSecret));
    // }
    private Key getKey() {
        String secretKey = "6LePO5jGQoHh5hPz5MPN3QBPZ5hBP7hGQePzQoHh3QEPz5hH7oZQePB7h3lH5M=="; 
        // Cette clé doit être sécurisée et gérée de manière appropriée
        
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Object getUsernameFromToken(String token){
        Claims claims=Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        return claims.get("username");
    }
    public boolean validateToken(String token){
        try {
            var a=Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
                return a.getExpiration().getTime()>new Date().getTime();
            
        } catch (MalformedJwtException e) {
            throw new RuntimeException("JWT invalide "+e.getMessage());
        }catch(ExpiredJwtException ex){
            throw new RuntimeException("Token a expiree"+ex.getMessage());
        }catch(IllegalArgumentException ill){
            throw new RuntimeException("Le contenu du Jwt ext vide "+ill.getMessage());
        }
    }
}
