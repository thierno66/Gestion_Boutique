package com.thierno.gestion_boutique.dto;

import java.util.List;


public record UserRequest(
    Integer id,
     String nom,
     String prenom,
     String email,
     String telephone,
     String password,
     List<String> roleNames
) {

}
