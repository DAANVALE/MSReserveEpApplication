package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.ClientModel;
import com.proyect.msreserveepapplication.model.EventModel;
import com.proyect.msreserveepapplication.model.TerraceModel;
import com.proyect.msreserveepapplication.repository.ClientRepo;
import com.proyect.msreserveepapplication.repository.EventRepo;
import com.proyect.msreserveepapplication.repository.TerraceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    
    @Autowired
    private final EventRepo eventRepo;

    private ClientRepo clientRepo;

    private TerraceRepo terraceRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Page<EventModel> findAll(Pageable pageable){
        return eventRepo.findAll(pageable);
    }

    public Optional<EventModel> findById(Integer id){
        return eventRepo.findById(id);
    }

    public EventModel saveEvent(EventModel eventModel){
        return eventRepo.save(eventModel);
    }

    public Page<EventModel> findByClientId(Integer clientId, Pageable pageable){
        Optional<ClientModel> client = clientRepo.findById(clientId);
        return client.map(clientModel -> findByClient(clientModel, pageable)).orElse(null);
    }

    public Page<EventModel> findByClient(ClientModel client, Pageable pageable){
        return eventRepo.findByClientModel(client,pageable);
    }

    public Page<EventModel> findByTerraceId(Integer terraceId, Pageable pageable){
        Optional<TerraceModel> terrace = terraceRepo.findById(terraceId);
        return terrace.map(terraceModel -> findByTerrace(terraceModel, pageable)).orElse(null);
    }

    public Page<EventModel> findByTerrace(TerraceModel terrace, Pageable pageable){
        return eventRepo.findByTerraceModel(terrace,pageable);
    }

    public EventModel deleteEvent(Integer id){
        EventModel eventModel = eventRepo.findById(id).get();
        eventRepo.delete(eventModel);
        return eventModel;
    }
}
