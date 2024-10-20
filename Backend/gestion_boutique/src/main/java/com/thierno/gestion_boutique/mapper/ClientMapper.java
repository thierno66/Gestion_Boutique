package com.thierno.gestion_boutique.mapper;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.ClientRequest;
import com.thierno.gestion_boutique.dto.ClientResponse;
import com.thierno.gestion_boutique.entite.Adresse;
import com.thierno.gestion_boutique.entite.Client;

@Service
public class ClientMapper {
    public Client toClient(ClientRequest request,Adresse adresse){
        return Client.builder()
                        .id(request.id())
                        .nom(request.nom())
                        .prenom(request.prenom())
                        .email(request.email())
                        .telephone(request.telephone())
                        .adresse(adresse)
                        .build();
    }
   public  ClientResponse fromClient(Client client){
        return new ClientResponse(client.getId(), client.getNom(),client.getPrenom(),client.getTelephone(),client.getEmail(),client.getAdresse());
    }
}
