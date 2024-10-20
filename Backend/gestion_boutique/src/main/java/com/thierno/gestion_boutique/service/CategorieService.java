package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.CategorieRequest;
import com.thierno.gestion_boutique.dto.CategorieResponse;

public interface CategorieService {
    CategorieResponse createCategorie(CategorieRequest request);
    List<CategorieResponse> getAllCategories();
    CategorieResponse getCategorieById(Integer id);
    CategorieResponse updateCategorie(CategorieRequest request,Integer id);
    boolean deleteCategorie(Integer id);
}
