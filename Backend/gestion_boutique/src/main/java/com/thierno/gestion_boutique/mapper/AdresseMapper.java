package com.thierno.gestion_boutique.mapper;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.AdresseRequest;
import com.thierno.gestion_boutique.dto.AdresseResponse;
import com.thierno.gestion_boutique.entite.Adresse;

@Service
public class AdresseMapper {
    public Adresse toAdresse(AdresseRequest request){
        return Adresse.builder()
                        .id(request.id())
                        .nom(request.nom())
                        .description(request.description())
                        .build();
    }
    public AdresseResponse fromAdresse(Adresse adresse){
        return new AdresseResponse(adresse.getId(),adresse.getNom(),adresse.getDescription());
    }
}
