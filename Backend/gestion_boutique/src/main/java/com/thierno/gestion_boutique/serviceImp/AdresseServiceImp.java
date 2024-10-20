package com.thierno.gestion_boutique.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.AdresseRequest;
import com.thierno.gestion_boutique.dto.AdresseResponse;
import com.thierno.gestion_boutique.entite.Adresse;
import com.thierno.gestion_boutique.mapper.AdresseMapper;
import com.thierno.gestion_boutique.repository.AdresseRepository;
import com.thierno.gestion_boutique.service.AdresseService;

@Service
public class AdresseServiceImp implements AdresseService {
    @Autowired
    AdresseRepository adresseRepository;
    @Autowired
    AdresseMapper adresseMapper;
    @Override
    public AdresseResponse createAdresse(AdresseRequest request) {
        
        return adresseMapper.fromAdresse(adresseRepository.save(adresseMapper.toAdresse(request)));
    }

    @Override
    public List<AdresseResponse> getAllAdresse() {
        return adresseRepository.findAll().stream().map(a->adresseMapper.fromAdresse(a)).toList();
    }

    @Override
    public AdresseResponse getAdresseById(Integer id) {
        Optional<Adresse> adresseOptional= adresseRepository.findById(id);
        if(!adresseOptional.isPresent()){
            throw new RuntimeException("Adresse not found");
        }

        return adresseMapper.fromAdresse(adresseOptional.get());
    }

    @Override
    public AdresseResponse updateAdresse(AdresseRequest request, Integer id) {
        Optional<Adresse> adresOptional =adresseRepository.findById(id);
        if(!adresOptional.isPresent()){
            throw new RuntimeException("Adresse not found");
        }
        var adresse=adresOptional.get();
        if(request.nom()!=null){
            adresse.setNom(request.nom());
        }
        if(request.description() != null){
            adresse.setDescription(request.description());
        }
        return adresseMapper.fromAdresse(adresseRepository.save(adresse));
    }

    @Override
    public boolean deleteAdresse(Integer id) {
        Optional<Adresse> optional = adresseRepository.findById(id);
        if(!optional.isPresent()){
            throw new RuntimeException("Adresse not found");
        }
        adresseRepository.deleteById(id);
        return true;
    }

}
