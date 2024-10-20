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

import com.thierno.gestion_boutique.dto.AdresseRequest;
import com.thierno.gestion_boutique.dto.AdresseResponse;
import com.thierno.gestion_boutique.service.AdresseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {
    @Autowired
    private AdresseService adresseService;
    @PostMapping
    public ResponseEntity<AdresseResponse> createAdresse(@Valid @RequestBody AdresseRequest request){
        return ResponseEntity.ok(adresseService.createAdresse(request));
    }
    @GetMapping
    public ResponseEntity<List<AdresseResponse>> getAllAdresse(){
        return ResponseEntity.ok(adresseService.getAllAdresse());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdresseResponse> getAdresseById(@PathVariable Integer id){
        return ResponseEntity.ok(adresseService.getAdresseById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdresseResponse> updateAdresseById(@RequestBody AdresseRequest request,@PathVariable Integer id){
        return ResponseEntity.ok(adresseService.updateAdresse(request, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAdresse(@PathVariable Integer id){
        return ResponseEntity.ok(adresseService.deleteAdresse(id));
    }
}
