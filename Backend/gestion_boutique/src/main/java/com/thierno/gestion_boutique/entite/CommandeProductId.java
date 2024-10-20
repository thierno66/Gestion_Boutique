package com.thierno.gestion_boutique.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class CommandeProductId implements Serializable {
    private Integer produitId;
    private Integer commandeId;
    
    public CommandeProductId() {
    }

    public CommandeProductId(Integer produitId, Integer commandeId) {
        this.produitId = produitId;
        this.commandeId = commandeId;
    }
    
}
