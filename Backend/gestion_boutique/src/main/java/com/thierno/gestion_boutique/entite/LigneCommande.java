package com.thierno.gestion_boutique.entite;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class LigneCommande {    
    @EmbeddedId
    private CommandeProductId id;
    private int quantite;
    
    @ManyToOne
    @MapsId("commandeId")
    private Commande commande;
    @ManyToOne
    @MapsId("produitId")
    private Produit produit;
}
