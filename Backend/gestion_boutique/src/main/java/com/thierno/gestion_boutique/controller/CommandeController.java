package com.thierno.gestion_boutique.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thierno.gestion_boutique.dto.CommandeRequest;
import com.thierno.gestion_boutique.dto.CommandeResponse;
import com.thierno.gestion_boutique.dto.LigneCommandeRequest;
import com.thierno.gestion_boutique.dto.LigneCommandeResponse;
import com.thierno.gestion_boutique.dto.VendreProduitRequest;
import com.thierno.gestion_boutique.dto.VendreProduitResponse;
import com.thierno.gestion_boutique.entite.CommandeProductId;
import com.thierno.gestion_boutique.entite.User;
import com.thierno.gestion_boutique.repository.UserRepository;
import com.thierno.gestion_boutique.service.CommandeService;
import com.thierno.gestion_boutique.service.LigneCommandeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private LigneCommandeService ligneCommandeService;

    @GetMapping
    public ResponseEntity<List<CommandeResponse>> getAllCommande(){
        return ResponseEntity.ok(commandeService.getAllCommande());
    }
    @PostMapping
    public ResponseEntity<CommandeResponse> createCommande(@Valid @RequestBody CommandeRequest request,@AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(commandeService.createCommande(request, user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommandeResponse> updateCommande(@RequestBody CommandeRequest request,@PathVariable Integer id ){
        return ResponseEntity.ok(commandeService.updateCommande(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCommande(@PathVariable Integer id){
        return ResponseEntity.ok(commandeService.deleteCommande(id));
    }
    @PostMapping("/ligne-commande")
    public ResponseEntity<LigneCommandeResponse> createLigneCommande(@Valid @RequestBody LigneCommandeRequest request){
        return ResponseEntity.ok(ligneCommandeService.creatLigneCommande(request));
    }
    @GetMapping("/{id}/ligne-commande")
    public ResponseEntity<List<LigneCommandeResponse>> getAllLigneByCommande(@PathVariable Integer id){
        return ResponseEntity.ok(ligneCommandeService.getAllLigneCommandeByCommande(id));
    }
    @GetMapping("/{commandeId}/produits/{produitId}")
    public ResponseEntity<LigneCommandeResponse> getLigneCommandeById(@PathVariable Integer commandeId,@PathVariable Integer produitId){
        CommandeProductId commandeProductId=new CommandeProductId(produitId, commandeId);
        return ResponseEntity.ok(ligneCommandeService.getLigneCommandeById(commandeProductId));
    }
    @PutMapping("/{commandeId}/produits/{produitId}")
    public ResponseEntity<LigneCommandeResponse> updateLineCommande(@RequestBody LigneCommandeRequest request, @PathVariable Integer commandeId,@PathVariable Integer produitId){
        CommandeProductId commandeProductId=new CommandeProductId(produitId, commandeId);
        return ResponseEntity.ok(ligneCommandeService.updateLiCommande(request, commandeProductId));
    }
    @DeleteMapping("/{commandeId}/produits/{produitId}")
    public ResponseEntity<Boolean> deleLineCommande(@PathVariable Integer commandeId,@PathVariable Integer produitId){
        CommandeProductId commandeProductId=new CommandeProductId(produitId, commandeId);
        return ResponseEntity.ok(ligneCommandeService.deleteLigneCommande(commandeProductId));
    }
    @PostMapping("/{commandeId}/vendre")
    public ResponseEntity<List<VendreProduitResponse>> vendreProduits(@RequestBody List<VendreProduitRequest> produits,@PathVariable Integer commandeId){
        System.out.println(produits);
        return ResponseEntity.ok(ligneCommandeService.vendreProduits(produits, commandeId));
    }
    @DeleteMapping("/{commandeId}/annuler-vente")
    public ResponseEntity<Boolean> annulerVente(@PathVariable Integer commandeId){
        return ResponseEntity.ok(ligneCommandeService.annulerVente(commandeId));
    }
}
