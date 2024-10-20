package com.thierno.gestion_boutique.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.CategorieRequest;
import com.thierno.gestion_boutique.dto.CategorieResponse;
import com.thierno.gestion_boutique.entite.Categorie;
import com.thierno.gestion_boutique.mapper.CategorieMapper;
import com.thierno.gestion_boutique.repository.CategorieRepository;
import com.thierno.gestion_boutique.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepository categorieRepository;
    private CategorieMapper categorieMapper;
    
    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @Override
    public CategorieResponse createCategorie(CategorieRequest request) {
        return categorieMapper.fromCategorie(categorieRepository.save(categorieMapper.toCategorie(request)));
    }

    @Override
    public List<CategorieResponse> getAllCategories() {
        return categorieRepository.findAll().stream().map(categorie->categorieMapper.fromCategorie(categorie)).toList();
    }

    @Override
    public CategorieResponse getCategorieById(Integer id) {
        return categorieMapper.fromCategorie(categorieRepository.findById(id).orElseThrow(() -> new RuntimeException("Categorie not found")));
    }

    @Override
    public CategorieResponse updateCategorie(CategorieRequest request, Integer id) {
        Categorie categorie =categorieRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        if(request.nom()!=null){
            categorie.setNom(request.nom());
        }
        if(request.description()!=null){
            categorie.setDescription(request.description());
        }

        return categorieMapper.fromCategorie(categorieRepository.save(categorie));
    }

    @Override
    public boolean deleteCategorie(Integer id) {
        Categorie categorie =categorieRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categorieRepository.delete(categorie);
        return true;
    }

}
