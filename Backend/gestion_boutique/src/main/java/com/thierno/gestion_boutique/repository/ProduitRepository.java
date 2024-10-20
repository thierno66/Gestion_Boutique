package com.thierno.gestion_boutique.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    List<Produit> findAllByCategoriesId(Integer id);
}
