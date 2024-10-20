package com.thierno.gestion_boutique.dto;



import jakarta.validation.constraints.NotNull;


public record RoleRequest(
     Integer id,
     @NotNull(message = "Le type de role ne peux pas etres null")
     String roleName
) {

}
