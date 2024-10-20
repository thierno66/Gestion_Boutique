package com.thierno.gestion_boutique.mapper;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.RoleRequest;
import com.thierno.gestion_boutique.dto.RoleResponse;
import com.thierno.gestion_boutique.entite.Role;
import com.thierno.gestion_boutique.entite.RoleType;

@Service
public class RoleMapper {

        public Role toRole(RoleRequest request){
            return Role.builder()
                        .id(request.id())
                        .roleName(RoleType.valueOf(request.roleName()))
                        .build();
        }
        public RoleResponse fromRole(Role role){
            return new RoleResponse(role.getId(), role.getRoleName().name());
        }
}
