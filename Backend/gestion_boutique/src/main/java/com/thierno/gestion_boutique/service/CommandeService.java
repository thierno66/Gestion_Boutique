package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.CommandeRequest;
import com.thierno.gestion_boutique.dto.CommandeResponse;
import com.thierno.gestion_boutique.entite.User;

public interface CommandeService {
    CommandeResponse createCommande(CommandeRequest request,User user);
    CommandeResponse updateCommande(CommandeRequest request,Integer id);
    Boolean deleteCommande(Integer id);
    List<CommandeResponse> getAllCommande();
    List<CommandeResponse> getAllCommandeByClient(Integer clientId);
    List<CommandeResponse> getAllCommandeByUser(String username);
    
    
}
