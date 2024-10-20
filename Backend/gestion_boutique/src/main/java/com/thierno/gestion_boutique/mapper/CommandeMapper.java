package com.thierno.gestion_boutique.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.CommandeRequest;
import com.thierno.gestion_boutique.dto.CommandeResponse;
import com.thierno.gestion_boutique.entite.Client;
import com.thierno.gestion_boutique.entite.Commande;
import com.thierno.gestion_boutique.entite.User;

@Service
public class CommandeMapper {
    public Commande toCommande(CommandeRequest request,Client client,User user){
        return Commande.builder()
                        .id(request.id())
                        .client(client)
                        .jour(LocalDateTime.now())
                        .numeroCommande(request.numeroCommande())
                        .user(user)
                        .build();
    }
    public CommandeResponse fromCommande(Commande commande){
        return new CommandeResponse(commande.getId() ,commande.getJour().format(DateTimeFormatter.ISO_DATE_TIME),commande.getNumeroCommande() ,commande.getClient(), commande.getUser());
    }
}
