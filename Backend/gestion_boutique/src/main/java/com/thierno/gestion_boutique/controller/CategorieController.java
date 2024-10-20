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

import com.thierno.gestion_boutique.dto.CategorieRequest;
import com.thierno.gestion_boutique.dto.CategorieResponse;
import com.thierno.gestion_boutique.service.CategorieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;
    @PostMapping
    public ResponseEntity<CategorieResponse> createCategorie(@Valid @RequestBody CategorieRequest request){
        return ResponseEntity.ok(categorieService.createCategorie(request));
    }
    @GetMapping
    public ResponseEntity<List<CategorieResponse>> getAllCategories(){
        return ResponseEntity.ok(categorieService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategorieResponse> getCategorieById(@PathVariable Integer id){
        return ResponseEntity.ok(categorieService.getCategorieById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategorieResponse> updadteCategorie(@RequestBody CategorieRequest request,@PathVariable Integer id){
        return ResponseEntity.ok(categorieService.updateCategorie(request, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategorie(@PathVariable Integer id){
        return ResponseEntity.ok(categorieService.deleteCategorie(id));
    }
}
