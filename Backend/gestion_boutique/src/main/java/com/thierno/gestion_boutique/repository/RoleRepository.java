package com.thierno.gestion_boutique.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thierno.gestion_boutique.entite.Role;
import com.thierno.gestion_boutique.entite.RoleType;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleType roleName);
    boolean existsByRoleName(RoleType roleName);

}
