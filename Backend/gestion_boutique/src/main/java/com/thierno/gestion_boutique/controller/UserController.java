package com.thierno.gestion_boutique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thierno.gestion_boutique.dto.AddroleUser;
import com.thierno.gestion_boutique.dto.CommandeResponse;
import com.thierno.gestion_boutique.dto.ConnexionRequest;
import com.thierno.gestion_boutique.dto.InscriptionWithRole;
import com.thierno.gestion_boutique.dto.UserRequest;
import com.thierno.gestion_boutique.dto.UserResponse;
import com.thierno.gestion_boutique.service.CommandeService;
import com.thierno.gestion_boutique.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private CommandeService commandeService;
    @Autowired
    UserService userService;
    @PostMapping("/inscription")
    public ResponseEntity<UserResponse> createUser(@RequestBody InscriptionWithRole user){
        return ResponseEntity.ok(userService.createUser(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserResponse updateUser(@RequestBody UserRequest user,@PathVariable Integer id){
        return userService.updateUser(user, id);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteuser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
    @PutMapping("/{userId}/role")
    public Boolean addRoleToUser(@PathVariable Integer userId,@RequestBody AddroleUser roleName){
        return userService.addRoleToUser(userId, roleName);
    }
    @PutMapping("/{id}/update-role")
    public Boolean deleteRoleToUser(@PathVariable Integer id,@RequestBody AddroleUser roleName){
        return userService.deleteRoleToUser(id, roleName);
    }
    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody ConnexionRequest connexionRequest){
        System.out.println("dans usercontroller " + connexionRequest.username());
        return ResponseEntity.ok(userService.connexion(connexionRequest));
    }
    @GetMapping("/commandes")
    public ResponseEntity<List<CommandeResponse>> getAllCommandeByUser(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(commandeService.getAllCommandeByUser(userDetails.getUsername()));
    }
}
