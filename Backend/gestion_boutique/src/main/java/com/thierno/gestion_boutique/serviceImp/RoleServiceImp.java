package com.thierno.gestion_boutique.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.RoleRequest;
import com.thierno.gestion_boutique.dto.RoleResponse;
import com.thierno.gestion_boutique.entite.Role;
import com.thierno.gestion_boutique.entite.RoleType;
import com.thierno.gestion_boutique.mapper.RoleMapper;
import com.thierno.gestion_boutique.repository.RoleRepository;
import com.thierno.gestion_boutique.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public RoleResponse createRole(RoleRequest role) {
        if(roleRepository.existsByRoleName(RoleType.valueOf(role.roleName()))){
            throw new IllegalArgumentException("Ce role existe deja");
        }
        Role role2=Role.builder().id(role.id()).roleName(RoleType.valueOf(role.roleName())).build();
        Role role3 = roleRepository.save(role2);
        return roleMapper.fromRole(role3);
    }

    @Override
    public List<RoleResponse> getAllRole() {
        return roleRepository.findAll().stream().map(role->roleMapper.fromRole(role)).toList();
    }

    @Override
    public RoleResponse getRoleById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoleById'");
    }

    @Override
    public RoleResponse getRoleByRoleName(String rolename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoleByRoleName'");
    }

}
