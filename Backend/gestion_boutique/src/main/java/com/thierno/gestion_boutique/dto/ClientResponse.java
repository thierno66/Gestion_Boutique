package com.thierno.gestion_boutique.dto;

import com.thierno.gestion_boutique.entite.Adresse;

public record ClientResponse(
     Integer id,
     String nom,
     String prenom,
     String telephone,
     String email,
     Adresse adresse
) {

}
