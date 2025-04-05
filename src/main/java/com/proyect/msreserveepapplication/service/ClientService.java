package com.proyect.msreserveepapplication.service;

import ch.qos.logback.core.net.server.Client;
import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.model.ClientModel;
import com.proyect.msreserveepapplication.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Page<ClientModel> getAllClients(Pageable pageable){
        return clientRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<ClientModel> getClientById(Integer id){
        return clientRepo.findById(id);
    }

    public ClientModel saveClient(ClientModel clientModel){
        return clientRepo.save(clientModel);
    }

    public ClientModel killClient(Integer clientId){

        Optional<ClientModel> clientModel = clientRepo.findById(clientId);

        ClientModel clientModelToKill = clientModel.get();
        clientModelToKill.setKilled((byte)1);

        if(clientModel.isPresent()){
            clientRepo.save(clientModelToKill);

            // TODO: Delete and set Kill every else

        }else{
            throw new ResourceNotFoundException("Client not found with id " + clientId);
        }

        return clientModelToKill;
    }
}
