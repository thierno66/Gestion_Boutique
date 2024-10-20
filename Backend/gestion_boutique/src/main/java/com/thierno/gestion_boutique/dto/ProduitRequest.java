package com.thierno.gestion_boutique.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;


public record ProduitRequest(
     Integer id,
     @NotNull(message = "Le non du categorie est Obligatoire")
     String nom,
     String description,
     @NotNull(message = "La quantite disponible est obligatoire")
     int quantiteDisponible,
     @NotNull(message = "Le prix d'achat pour une unite est obligatoire")
     double prixAchat,
     @NotNull(message = "Le pris de ventes est obligatoires")
     double prixVente,
     List<Integer> categorieIds
) {

}
