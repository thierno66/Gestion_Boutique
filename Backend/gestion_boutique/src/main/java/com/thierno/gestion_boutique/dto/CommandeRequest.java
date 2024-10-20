package com.thierno.gestion_boutique.dto;

import jakarta.validation.constraints.NotNull;

public record CommandeRequest(
     Integer id,
     @NotNull(message = "Le client est obligatoire pour passer une commande")
     String numeroCommande,
     Integer clientId
) {

}
