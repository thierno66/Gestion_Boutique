package com.thierno.gestion_boutique.dto;

import java.util.List;

import com.thierno.gestion_boutique.entite.Categorie;

public record ProduitResponse(
     Integer id,
     String nom,
     String description,
     int quantiteDisponible,
     double prixAchat,
     double prixVente,
     List<Categorie> categories
) {

}
