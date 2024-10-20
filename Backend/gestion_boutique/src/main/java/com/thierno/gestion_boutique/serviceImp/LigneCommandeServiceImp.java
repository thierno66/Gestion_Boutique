package com.thierno.gestion_boutique.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thierno.gestion_boutique.dto.LigneCommandeRequest;
import com.thierno.gestion_boutique.dto.LigneCommandeResponse;
import com.thierno.gestion_boutique.dto.VendreProduitRequest;
import com.thierno.gestion_boutique.dto.VendreProduitResponse;
import com.thierno.gestion_boutique.entite.Commande;
import com.thierno.gestion_boutique.entite.CommandeProductId;
import com.thierno.gestion_boutique.entite.LigneCommande;
import com.thierno.gestion_boutique.entite.Produit;
import com.thierno.gestion_boutique.mapper.LigneCommandeMapper;
import com.thierno.gestion_boutique.repository.CommandeRepository;
import com.thierno.gestion_boutique.repository.LigneCommandeRepository;
import com.thierno.gestion_boutique.repository.ProduitRepository;
import com.thierno.gestion_boutique.service.LigneCommandeService;

@Service
public class LigneCommandeServiceImp implements LigneCommandeService {

    private LigneCommandeRepository ligneCommandeRepository;
    private LigneCommandeMapper ligneCommandeMapper;
    private ProduitRepository produitRepository;
    private CommandeRepository commandeRepository;
    
    public LigneCommandeServiceImp(LigneCommandeRepository ligneCommandeRepository,
            LigneCommandeMapper ligneCommandeMapper, ProduitRepository produitRepository,
            CommandeRepository commandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.ligneCommandeMapper = ligneCommandeMapper;
        this.produitRepository = produitRepository;
        this.commandeRepository = commandeRepository;
    }

    @Override
    @Transactional
    public LigneCommandeResponse creatLigneCommande(LigneCommandeRequest request) {
        Commande commande = commandeRepository.findById(request.commandeId()).orElseThrow(() -> new RuntimeException("Commande not found"));
        Produit produit = produitRepository.findById(request.productId()).orElseThrow(()->new RuntimeException("Produit not found"));
        if(request.quantite()>produit.getQuantiteDisponible()){
            throw new RuntimeException("Desole mais la quantite est insuffisants");
        }
        produit.setQuantiteDisponible(produit.getQuantiteDisponible()-request.quantite());
        produitRepository.save(produit);
        return ligneCommandeMapper.fromLigneCommande(ligneCommandeRepository.save(ligneCommandeMapper.toLigneCommande(request, commande, produit)));
    }

    @Override
    public List<LigneCommandeResponse> getAllLigneCommandeByCommande(Integer commandeId) {
        return ligneCommandeRepository.findAllByIdCommandeId(commandeId).stream().map(c->ligneCommandeMapper.fromLigneCommande(c)).toList();
    }

    @Override
    public LigneCommandeResponse getLigneCommandeById(CommandeProductId id) {
        return ligneCommandeMapper.fromLigneCommande(ligneCommandeRepository.findById(id).orElseThrow(()->
        new RuntimeException("Ligne de commanse not found")));
    }

    @Override
    @Transactional
    public LigneCommandeResponse updateLiCommande(LigneCommandeRequest request, CommandeProductId id) {
        LigneCommande ligneCommande= ligneCommandeRepository.findById(id).orElseThrow(()->new RuntimeException("Ligne de commande not found"));
        Produit produit = produitRepository.findById(request.productId()).orElseThrow(()->new RuntimeException("Produit not found"));
        if(request.quantite()!=0 ){
            produit.setQuantiteDisponible(produit.getQuantiteDisponible()+ligneCommande.getQuantite());
            if(produit.getQuantiteDisponible()> request.quantite()){
                produit.setQuantiteDisponible(produit.getQuantiteDisponible()-request.quantite());
                produitRepository.save(produit);
                ligneCommande.setQuantite(request.quantite());
            }   
            else
                throw new RuntimeException("Desole mais la quantite est choisit est indisponible");
        }
        
        return ligneCommandeMapper.fromLigneCommande(ligneCommandeRepository.save(ligneCommande));
    }

    @Override
    @Transactional
    public boolean deleteLigneCommande(CommandeProductId id) {
        LigneCommande ligneCommande = ligneCommandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ligne de commande not found"));
        Produit produit= produitRepository.findById(id.getProduitId()).orElseThrow(() -> new RuntimeException("Produit not found"));
        produit.setQuantiteDisponible(produit.getQuantiteDisponible()+ligneCommande.getQuantite());
        produitRepository.save(produit);
        ligneCommandeRepository.delete(ligneCommande);
        return true;
    }

    @Override
    @Transactional
    public List<VendreProduitResponse> vendreProduits(List<VendreProduitRequest> produits,Integer commandeId) {
        Commande commande = commandeRepository.findById(commandeId).orElseThrow(() -> new RuntimeException("Commande not found"));
        List<Produit> updatedProduit=new ArrayList<>();
        List<LigneCommande> ligneCommandes=new ArrayList<>();
        produits.forEach(produit->{
            Produit produit1 =produitRepository.findById(produit.produitId()).orElseThrow(() -> new RuntimeException("Produit not found"));
            if(produit1.getQuantiteDisponible()<produit.quantite()){
                throw new RuntimeException("La quantite du produit "+ produit1.getNom()+" est indisponible");
            }
            produit1.setQuantiteDisponible(produit1.getQuantiteDisponible()-produit.quantite());
            updatedProduit.add(produit1);
            CommandeProductId ids=new CommandeProductId(produit.produitId(), commandeId);
            LigneCommande la=new LigneCommande(ids, produit.quantite(), commande, produit1);
            ligneCommandes.add(la);
        });
        produitRepository.saveAll(updatedProduit);
        return ligneCommandeRepository.saveAll(ligneCommandes).stream().map(l->ligneCommandeMapper.toVendreProduit(l)).toList();
    }

    @Override
    @Transactional
    public Boolean annulerVente(Integer commandeId) {
        List<LigneCommande> ligneCommandes= ligneCommandeRepository.findAllByIdCommandeId(commandeId);
        ligneCommandes.forEach(ligneCommande->{
            Integer produitId=ligneCommande.getId().getProduitId();
            Produit produit= produitRepository.findById(produitId).orElseThrow(() -> new RuntimeException("Produit not found"));
            produit.setQuantiteDisponible(produit.getQuantiteDisponible()+ligneCommande.getQuantite());
            produitRepository.save(produit);
            ligneCommandeRepository.delete(ligneCommande);
        });
        return true;
    }   
}
