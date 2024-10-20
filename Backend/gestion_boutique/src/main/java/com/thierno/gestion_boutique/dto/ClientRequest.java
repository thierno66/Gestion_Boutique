package com.thierno.gestion_boutique.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record ClientRequest(
     Integer id,
     @NotNull(message = "Le nom ne peut pas etre null")
     String nom,
     @NotNull(message = "Le prenom ne peut pas etre null")
     String prenom,
     @NotNull(message = "Le telephone ne doit pas etre null")
     String telephone,
     @Email(message = "le nail n'est pas valide")
     String email,
     Integer adresseId
) {

}
