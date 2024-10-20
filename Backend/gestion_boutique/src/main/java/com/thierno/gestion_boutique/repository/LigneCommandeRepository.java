package com.thierno.gestion_boutique.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.CommandeProductId;
import com.thierno.gestion_boutique.entite.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,CommandeProductId> {
    List<LigneCommande> findAllByIdCommandeId(Integer commandeId);
}
