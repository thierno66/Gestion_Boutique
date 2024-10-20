package com.thierno.gestion_boutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
