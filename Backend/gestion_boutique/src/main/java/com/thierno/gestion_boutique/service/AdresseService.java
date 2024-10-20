package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.AdresseRequest;
import com.thierno.gestion_boutique.dto.AdresseResponse;

public interface AdresseService {
    AdresseResponse createAdresse(AdresseRequest request);
    List<AdresseResponse> getAllAdresse();
    AdresseResponse getAdresseById(Integer id);
    AdresseResponse updateAdresse(AdresseRequest request,Integer id);
    boolean deleteAdresse(Integer id);
}
