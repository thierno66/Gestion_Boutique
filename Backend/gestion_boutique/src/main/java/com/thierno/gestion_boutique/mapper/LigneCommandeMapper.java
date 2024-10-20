package com.thierno.gestion_boutique.mapper;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.LigneCommandeRequest;
import com.thierno.gestion_boutique.dto.LigneCommandeResponse;
import com.thierno.gestion_boutique.dto.VendreProduitResponse;
import com.thierno.gestion_boutique.entite.Commande;
import com.thierno.gestion_boutique.entite.CommandeProductId;
import com.thierno.gestion_boutique.entite.LigneCommande;
import com.thierno.gestion_boutique.entite.Produit;

@Service
public class LigneCommandeMapper {
    public LigneCommande toLigneCommande(LigneCommandeRequest request,Commande commande,Produit produit){
        CommandeProductId commandeProductId=new CommandeProductId(request.productId(),request.commandeId());
        System.out.println("Dans mapper "+request.quantite());
        return LigneCommande
                            .builder()
                            .id(commandeProductId)
                            .commande(commande)
                            .produit(produit)
                            .quantite(request.quantite())
                            .build();
    }

    public LigneCommandeResponse fromLigneCommande(LigneCommande ligneCommande){
        return new LigneCommandeResponse(ligneCommande.getProduit(), ligneCommande.getCommande(), ligneCommande.getQuantite());
    }
    public VendreProduitResponse toVendreProduit(LigneCommande commande){
        return new VendreProduitResponse(commande.getCommande().getId(), commande.getProduit().getId(),
                 commande.getCommande().getJour(), commande.getQuantite(), commande.getProduit().getNom(), 
                 commande.getProduit().getPrixVente());
    }
    
}
