package com.thierno.gestion_boutique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thierno.gestion_boutique.dto.ProduitRequest;
import com.thierno.gestion_boutique.dto.ProduitResponse;
import com.thierno.gestion_boutique.service.ProduitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitResponse> createProduit(@Valid @RequestBody ProduitRequest request){
        return ResponseEntity.ok(produitService.createProduit(request));
    }
    @GetMapping
    public ResponseEntity<List<ProduitResponse>> getAllProduits(){
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponse> getProduitById(@PathVariable Integer id){
        return ResponseEntity.ok(produitService.getProduitById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponse> updateProduit(@RequestBody ProduitRequest request,@PathVariable Integer id){
        return ResponseEntity.ok(produitService.updateProduit(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduit(@PathVariable Integer id){
        return ResponseEntity.ok(produitService.deleteProduit(id));
    }


    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<ProduitResponse>> getAllProduitByIdCategorie(@PathVariable Integer categorieId){
        return ResponseEntity.ok(produitService.getAllProduitByCategorieId(categorieId));
    }
}
