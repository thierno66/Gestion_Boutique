package com.thierno.gestion_boutique.dto;

import jakarta.validation.constraints.NotNull;

public record CategorieRequest(
     Integer id,
     @NotNull(message = "Le nom de la categorie des produits est obligatoires")
     String nom,
     String description
) {

}
