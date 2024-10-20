package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.RoleRequest;
import com.thierno.gestion_boutique.dto.RoleResponse;

public interface RoleService {
    RoleResponse createRole(RoleRequest role);
    List<RoleResponse> getAllRole();
    RoleResponse getRoleById(Integer id);
    RoleResponse getRoleByRoleName(String rolename);

}
