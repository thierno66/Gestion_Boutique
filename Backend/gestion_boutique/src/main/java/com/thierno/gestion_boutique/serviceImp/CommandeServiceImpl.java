package com.thierno.gestion_boutique.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.dto.CommandeRequest;
import com.thierno.gestion_boutique.dto.CommandeResponse;
import com.thierno.gestion_boutique.entite.Client;
import com.thierno.gestion_boutique.entite.Commande;
import com.thierno.gestion_boutique.entite.User;
import com.thierno.gestion_boutique.mapper.CommandeMapper;
import com.thierno.gestion_boutique.repository.ClientRepository;
import com.thierno.gestion_boutique.repository.CommandeRepository;
import com.thierno.gestion_boutique.repository.UserRepository;
import com.thierno.gestion_boutique.service.CommandeService;

@Service
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    private CommandeMapper commandeMapper;
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    
    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeMapper commandeMapper,
            ClientRepository clientRepository, UserRepository userRepository) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }
    @Override
    public CommandeResponse createCommande(CommandeRequest request,User user) {
        Client client= clientRepository.findById(request.clientId()).orElseThrow(()-> new RuntimeException("Client not Found"));
        return commandeMapper.fromCommande(commandeRepository.save(commandeMapper.toCommande(request, client, user)));
    }
    @Override
    public CommandeResponse updateCommande(CommandeRequest request, Integer id) {
        Client client= clientRepository.findById(request.clientId()).orElseThrow(()-> new RuntimeException("Client not Found"));
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande not Found"));
        commande.setClient(client);
        return commandeMapper.fromCommande(commandeRepository.save( commande));
    }
    @Override
    public Boolean deleteCommande(Integer id) {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande not found"));
        commandeRepository.delete(commande);
        return true;
    }
    @Override
    public List<CommandeResponse> getAllCommande() {
        return commandeRepository.findAll().stream().map(commande->commandeMapper.fromCommande(commande)).toList();
    }
    @Override
    public List<CommandeResponse> getAllCommandeByClient(Integer clientId) {
        clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client Not Found"));
        return commandeRepository.findAllByClientId(clientId).stream().map(com->commandeMapper.fromCommande(com)).toList();
    }
    @Override
    public List<CommandeResponse> getAllCommandeByUser(String username) {
        User user = userRepository.findByEmail(username);
        return commandeRepository.findAllByUserId(user.getId()).stream().map(com->commandeMapper.fromCommande(com)).toList();
    }

}
