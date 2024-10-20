package com.thierno.gestion_boutique.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.ClientRequest;
import com.thierno.gestion_boutique.dto.ClientResponse;
import com.thierno.gestion_boutique.entite.Adresse;
import com.thierno.gestion_boutique.entite.Client;
import com.thierno.gestion_boutique.mapper.ClientMapper;
import com.thierno.gestion_boutique.repository.AdresseRepository;
import com.thierno.gestion_boutique.repository.ClientRepository;
import com.thierno.gestion_boutique.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private ClientMapper  clientMapper;
    private AdresseRepository adresseRepository;
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper,AdresseRepository adresseRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.adresseRepository=adresseRepository;
    }

    @Override
    public ClientResponse createClient(ClientRequest request) {
        Adresse optional=adresseRepository.findById(request.adresseId()).orElse(null);
        return clientMapper.fromClient(clientRepository.save(clientMapper.toClient(request, optional)));
    }

    @Override
    public List<ClientResponse> getAllClient() {
        return clientRepository.findAll().stream().map(client->clientMapper.fromClient(client)).toList();
    }

    @Override
    public ClientResponse getClientById(Integer id) {
        Client optional =clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not fount"));
        return clientMapper.fromClient(optional);
    }

    @Override
    public ClientResponse updateClient(ClientRequest request, Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        System.out.println(client.getAdresse()+"dans updatecllient service");
        if(request.nom()!=null){
            client.setNom(request.nom());
        }
        if(request.prenom()!=null){
            client.setPrenom(request.prenom());
        }
        if(request.email()!=null){
            client.setEmail(request.email());
        }
        if(request.telephone() != null){
            client.setTelephone(request.telephone());
        }
        if(request.adresseId()!=null){
            Optional<Adresse> adresse = adresseRepository.findById(request.adresseId());
            if (adresse.isPresent()) {
                client.setAdresse(adresse.get());
            }
        }
        return clientMapper.fromClient(clientRepository.save(client));

    }

    @Override
    public boolean deleteClient(Integer id) {
       Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found "));
       clientRepository.delete(client);
       return true;
    }

}
