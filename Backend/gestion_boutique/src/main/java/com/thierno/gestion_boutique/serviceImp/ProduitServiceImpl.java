package com.thierno.gestion_boutique.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.ProduitRequest;
import com.thierno.gestion_boutique.dto.ProduitResponse;
import com.thierno.gestion_boutique.entite.Categorie;
import com.thierno.gestion_boutique.entite.Produit;
import com.thierno.gestion_boutique.mapper.ProduitMappeur;
import com.thierno.gestion_boutique.repository.CategorieRepository;
import com.thierno.gestion_boutique.repository.ProduitRepository;
import com.thierno.gestion_boutique.service.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService {
    private ProduitRepository produitRepository;
    private ProduitMappeur produitMappeur;
    private CategorieRepository categorieRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMappeur produitMappeur,CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.produitMappeur = produitMappeur;
        this.categorieRepository=categorieRepository;
    }

    @Override
    public ProduitResponse createProduit(ProduitRequest request) {
        List<Categorie> categories=categorieRepository.findAllById(request.categorieIds());
        return produitMappeur.fromProduit(produitRepository.save(produitMappeur.toProduit(request, categories)));
    }

    @Override
    public List<ProduitResponse> getAllProduits() {
        return produitRepository.findAll().stream().map(produit->produitMappeur.fromProduit(produit)).toList();
    }

    @Override
    public ProduitResponse getProduitById(Integer id) {
        return produitMappeur.fromProduit(produitRepository.findById(id)
                                            .orElseThrow(() -> new RuntimeException("Produit not found")));
    }

    @Override
    public ProduitResponse updateProduit(ProduitRequest request, Integer id) {
        Produit produit=produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit not found"));
        if(request.nom()!=null){
            produit.setNom(request.nom());
        }
        if(request.description()!=null){
            produit.setDescription(request.description());
        }
        if(request.quantiteDisponible()!=0){
            produit.setQuantiteDisponible(produit.getQuantiteDisponible()+request.quantiteDisponible());
        }
        if(request.prixAchat()!=0){
            produit.setPrixAchat(request.prixAchat());
        }
        if(request.prixVente()!=0){
            produit.setPrixVente(request.prixVente());
        }
        if(request.categorieIds()!=null){
            List<Integer> idsIndatasource=produit.getCategories().stream().map(c->c.getId()).toList();
            for(int i=0;i<idsIndatasource.size();i++){
                if(!request.categorieIds().contains(idsIndatasource.get(i))){
                    produit.getCategories().remove(i);
                }
            }
            for(int i=0;i<request.categorieIds().size();i++){
                if(!idsIndatasource.contains(request.categorieIds().get(i))){
                    Categorie categorie= categorieRepository.findById(request.categorieIds().get(i)).get();
                    produit.getCategories().add(categorie);
                }
        }
    }
    return produitMappeur.fromProduit(produitRepository.save(produit));
    }

    @Override
    public boolean deleteProduit(Integer id) {
        Produit produit = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit Not Found"));
        produitRepository.delete(produit);
        return true;
    }

    @Override
    public List<ProduitResponse> getAllProduitByCategorieId(Integer categoerieId) {
       return produitRepository.findAllByCategoriesId(categoerieId).stream().map(produit->produitMappeur.fromProduit(produit)).toList();
    }

}
