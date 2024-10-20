package com.thierno.gestion_boutique.dto;

import jakarta.validation.constraints.NotNull;

public record AdresseRequest(
    Integer id,
    @NotNull(message = "Le nom de l'dresse est obligatoire")
     String nom,
     String description
) {

}
