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

    @Autowired
    private final ClientRepo clientRepo;

    @Autowired
    private final TerraceRepo terraceRepo;

    @Autowired
    public EventService(EventRepo eventRepo, ClientRepo clientRepo, TerraceRepo terraceRepo) {
        this.eventRepo = eventRepo;
        this.clientRepo = clientRepo;
        this.terraceRepo = terraceRepo;
    }

    public Page<EventModel> getAllEvents(Pageable pageable){
        return eventRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<EventModel> getEventById(Integer id){
        return eventRepo.findById(id);
    }

    public EventModel saveEvent(EventModel eventModel){
        return eventRepo.save(eventModel);
    }

    public Page<EventModel> getEventsByClientId(Integer clientId, Pageable pageable){
        Optional<ClientModel> client = clientRepo.findById(clientId);
        return client.map(clientModel -> getEventsByClient(clientModel, pageable)).orElse(null);
    }

    public Page<EventModel> getEventsByClient(ClientModel client, Pageable pageable){
        return eventRepo.findByClientModel(client,pageable);
    }

    public Page<EventModel> getEventsByTerraceId(Integer terraceId, Pageable pageable){
        Optional<TerraceModel> terrace = terraceRepo.findById(terraceId);
        return terrace.map(terraceModel -> getEventsByTerrace(terraceModel, pageable)).orElse(null);
    }

    public Page<EventModel> getEventsByTerrace(TerraceModel terrace, Pageable pageable){
        return eventRepo.findByTerraceModel(terrace,pageable);
    }

    public EventModel deleteEvent(Integer id){
        EventModel eventModel = eventRepo.findById(id).get();
        eventRepo.delete(eventModel);
        return eventModel;
    }
}
