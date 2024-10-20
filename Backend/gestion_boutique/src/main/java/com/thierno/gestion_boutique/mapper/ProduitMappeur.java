package com.thierno.gestion_boutique.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.ProduitRequest;
import com.thierno.gestion_boutique.dto.ProduitResponse;
import com.thierno.gestion_boutique.entite.Categorie;
import com.thierno.gestion_boutique.entite.Produit;

@Service
public class ProduitMappeur {
    public Produit toProduit(ProduitRequest request,List<Categorie> categories){
        return Produit.builder()
                        .id(request.id())
                        .nom(request.nom())
                        .description(request.description())
                        .quantiteDisponible(request.quantiteDisponible())
                        .prixAchat(request.prixAchat())
                        .prixVente(request.prixVente())
                        .categories(categories)
                        .build();
    }
    public ProduitResponse fromProduit(Produit produit){
        return new ProduitResponse(produit.getId(),produit.getNom(),
                                   produit.getDescription(), produit.getQuantiteDisponible(),
                                    produit.getPrixAchat(), produit.getPrixVente(), produit.getCategories());
    }
}
