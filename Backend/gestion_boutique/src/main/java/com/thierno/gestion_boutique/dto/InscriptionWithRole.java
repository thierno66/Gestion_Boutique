package com.thierno.gestion_boutique.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record InscriptionWithRole(
    Integer id,
    @NotNull(message = "le nom ne  doit pas etre null")
     String nom,
     @NotNull(message = "le prenom ne doit pas etre null")
     String prenom,
     @NotNull(message = "l\'email ne peux pas etre null")
     @Email(message = "Invalid email")
     String email,
     @NotNull(message = "le numero telephone ne  doit pas etre null")
     String telephone,
     String password,
     @NotNull(message = "l\'utilisateur doit avoir au moins une roles")
     List<String> roleName
) {

}
