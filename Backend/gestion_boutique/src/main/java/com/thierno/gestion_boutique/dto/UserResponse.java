package com.thierno.gestion_boutique.dto;

import java.util.List;

import com.thierno.gestion_boutique.entite.Role;


public record UserResponse(
     Integer id,
     String nom,
     String prenom,
     String email,
     String telephone,
     List<Role> roles
) {

}
