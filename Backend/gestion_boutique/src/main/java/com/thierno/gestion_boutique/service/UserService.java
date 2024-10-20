package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.AddroleUser;
import com.thierno.gestion_boutique.dto.ConnexionRequest;
import com.thierno.gestion_boutique.dto.InscriptionWithRole;
import com.thierno.gestion_boutique.dto.UserRequest;
import com.thierno.gestion_boutique.dto.UserResponse;

public interface UserService {
    UserResponse createUser(InscriptionWithRole user);
    List<UserResponse> getAllUser();
    UserResponse getUserById(Integer id);
    UserResponse updateUser(UserRequest user,Integer id);
    Boolean deleteUser(Integer id);
    Boolean addRoleToUser(Integer userId, AddroleUser roleName);
    Boolean deleteRoleToUser(Integer userId,AddroleUser roleName);
    String connexion(ConnexionRequest request);

}
