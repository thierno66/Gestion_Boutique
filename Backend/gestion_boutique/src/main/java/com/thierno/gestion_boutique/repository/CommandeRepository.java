package com.thierno.gestion_boutique.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.Commande;

public interface CommandeRepository extends JpaRepository<Commande,Integer> {
    List<Commande> findAllByClientId(Integer id);
    List<Commande> findAllByUserId(Integer id);

}
