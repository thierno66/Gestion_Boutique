package com.thierno.gestion_boutique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thierno.gestion_boutique.dto.RoleRequest;
import com.thierno.gestion_boutique.dto.RoleResponse;
import com.thierno.gestion_boutique.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping
    public RoleResponse createRole(@RequestBody RoleRequest roleRequest){
        return roleService.createRole(roleRequest);
    }
    @GetMapping
    public List<RoleResponse> getAllRole(){
        return roleService.getAllRole();
    }

}
