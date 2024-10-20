package com.thierno.gestion_boutique.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thierno.gestion_boutique.dto.ClientRequest;
import com.thierno.gestion_boutique.dto.ClientResponse;
import com.thierno.gestion_boutique.dto.CommandeResponse;
import com.thierno.gestion_boutique.service.ClientService;
import com.thierno.gestion_boutique.service.CommandeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private ClientService clientService;
    private CommandeService commandeService;

    public ClientController(ClientService clientService,CommandeService commandeService) {
        this.clientService = clientService;
        this.commandeService=commandeService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request){
        return ResponseEntity.ok(clientService.createClient(request));
    }
    
    
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClient(){
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping("/{id}")
     public ResponseEntity<ClientResponse> getClientById(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.getClientById(id));
     }

     @PutMapping("/{id}")
     public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientRequest request,@PathVariable Integer id){
        System.out.println(id+" Dans updateClient");
        return ResponseEntity.ok(clientService.updateClient(request, id));
     }

     
     @DeleteMapping("/{id}")
     public ResponseEntity<Boolean> deleteClient(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.deleteClient(id));
     }
     
     
     @GetMapping("/{id}/commandes")
     public ResponseEntity<List<CommandeResponse>> getAllCommandeByClient(@PathVariable Integer id){
        return ResponseEntity.ok(commandeService.getAllCommandeByClient(id));
     }
}
