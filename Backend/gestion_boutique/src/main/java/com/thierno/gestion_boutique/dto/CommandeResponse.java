package com.thierno.gestion_boutique.dto;

import java.time.LocalDateTime;

import com.thierno.gestion_boutique.entite.Client;
import com.thierno.gestion_boutique.entite.User;

public record CommandeResponse(
    Integer id,
    String jour,
    String numeroCommande,
    Client client,
    User user
) {

}
