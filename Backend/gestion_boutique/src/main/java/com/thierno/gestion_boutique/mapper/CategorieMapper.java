package com.thierno.gestion_boutique.mapper;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.CategorieRequest;
import com.thierno.gestion_boutique.dto.CategorieResponse;
import com.thierno.gestion_boutique.entite.Categorie;

@Service
public class CategorieMapper {
    public Categorie toCategorie(CategorieRequest request){
        return Categorie.builder()
                        .id(request.id())
                        .nom(request.nom())
                        .description(request.description())
                        .build();
    }

    public CategorieResponse fromCategorie(Categorie categorie){
        return new CategorieResponse(categorie.getId(), categorie.getNom(), categorie.getDescription());
    }
}
