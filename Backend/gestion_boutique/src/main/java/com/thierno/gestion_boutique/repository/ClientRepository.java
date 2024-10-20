package com.thierno.gestion_boutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {

}
