package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.ProduitRequest;
import com.thierno.gestion_boutique.dto.ProduitResponse;

public interface ProduitService {
    ProduitResponse createProduit(ProduitRequest request);
    List<ProduitResponse> getAllProduits();
    ProduitResponse getProduitById(Integer id);
    ProduitResponse updateProduit(ProduitRequest request,Integer id);
    boolean deleteProduit(Integer id);
    List<ProduitResponse> getAllProduitByCategorieId(Integer categoerieId);
}
