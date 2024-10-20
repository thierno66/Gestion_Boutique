package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.LigneCommandeRequest;
import com.thierno.gestion_boutique.dto.LigneCommandeResponse;
import com.thierno.gestion_boutique.dto.VendreProduitRequest;
import com.thierno.gestion_boutique.dto.VendreProduitResponse;
import com.thierno.gestion_boutique.entite.CommandeProductId;

public interface LigneCommandeService {
    LigneCommandeResponse creatLigneCommande(LigneCommandeRequest request);
    List<LigneCommandeResponse> getAllLigneCommandeByCommande(Integer commandeId);
    LigneCommandeResponse getLigneCommandeById(CommandeProductId id);
    LigneCommandeResponse updateLiCommande(LigneCommandeRequest request,CommandeProductId id);
    boolean deleteLigneCommande(CommandeProductId id);
    List<VendreProduitResponse> vendreProduits(List<VendreProduitRequest> produits,Integer commandeId);
    Boolean annulerVente(Integer commandeId);
}
