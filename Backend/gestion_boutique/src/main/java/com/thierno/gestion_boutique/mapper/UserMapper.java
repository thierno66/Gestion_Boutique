package com.thierno.gestion_boutique.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.InscriptionWithRole;
import com.thierno.gestion_boutique.dto.UserResponse;
import com.thierno.gestion_boutique.entite.Role;
import com.thierno.gestion_boutique.entite.User;
@Service
public class UserMapper {
    public UserResponse fromUser(User user){
        return new UserResponse(
            user.getId(),user.getNom(),user.getPrenom(), user.getEmail(),
             user.getTelephone(),user.getRoles());
    }

    public User toUser(InscriptionWithRole userRequest,List<Role> roles,String password){
        return User.builder()
                .id(userRequest.id())
                .email(userRequest.email())
                .nom(userRequest.nom())
                .prenom(userRequest.prenom())
                .password(password)
                .telephone(userRequest.telephone())
                .roles(roles)
                .build();
    }

}
