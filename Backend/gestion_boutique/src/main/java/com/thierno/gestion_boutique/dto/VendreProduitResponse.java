package com.thierno.gestion_boutique.dto;

import java.time.LocalDateTime;

public record VendreProduitResponse(
     Integer commandeId,
     Integer produitId,
     LocalDateTime jour,
     int quantite,
     String nomProduit,
     double prixVente
) {

}
