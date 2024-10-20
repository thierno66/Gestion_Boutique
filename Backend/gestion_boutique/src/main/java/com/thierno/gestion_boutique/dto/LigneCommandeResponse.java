package com.thierno.gestion_boutique.dto;

import com.thierno.gestion_boutique.entite.Commande;
import com.thierno.gestion_boutique.entite.Produit;

public record LigneCommandeResponse(
    Produit produit,
    Commande commande,
    int quantite
) {

}
