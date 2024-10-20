package com.thierno.gestion_boutique.dto;

import jakarta.validation.constraints.NotNull;

public record LigneCommandeRequest(
    @NotNull(message = "Le produit est obligatoire")
    Integer productId,
    @NotNull(message = "La commande est obligatoire")
    Integer commandeId,
    int quantite
) {

}
