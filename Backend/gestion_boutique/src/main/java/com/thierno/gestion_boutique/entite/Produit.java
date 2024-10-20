package com.thierno.gestion_boutique.entite;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
    @Column(name = "quantite_disponible")
    private int quantiteDisponible;
    @Column(name = "prix_achat")
    private double prixAchat;
    @Column(name = "prix_vente")
    private double prixVente;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Categorie> categories;
}
