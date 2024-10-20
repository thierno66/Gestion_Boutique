package com.thierno.gestion_boutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

}
