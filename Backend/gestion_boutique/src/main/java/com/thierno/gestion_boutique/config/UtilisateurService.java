package com.thierno.gestion_boutique.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.entite.Role;
import com.thierno.gestion_boutique.repository.UserRepository;

@Service
public class UtilisateurService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.thierno.gestion_boutique.entite.User user = userRepository.findByEmail(username);
        System.out.println("username "+username);
        UserDetails userDetails= User.withUsername(user.getEmail())
                                .password(user.getPassword())
                                .authorities(mapRoleToAutority(user.getRoles()))
                                .build();
        return userDetails;
    }

    Collection<GrantedAuthority> mapRoleToAutority(List<Role> roles){
        return roles.stream().map(r->new SimpleGrantedAuthority(r.getRoleName().name())).collect(Collectors.toList());
    }


}
