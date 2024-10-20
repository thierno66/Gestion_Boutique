package com.thierno.gestion_boutique.service;

import java.util.List;

import com.thierno.gestion_boutique.dto.ClientRequest;
import com.thierno.gestion_boutique.dto.ClientResponse;

public interface ClientService {
    ClientResponse createClient(ClientRequest request);
    List<ClientResponse> getAllClient();
    ClientResponse getClientById(Integer id);
    ClientResponse updateClient(ClientRequest request,Integer id);
    boolean deleteClient(Integer id);

}
