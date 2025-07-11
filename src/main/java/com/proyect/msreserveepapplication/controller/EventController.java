package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.EventModel;

import com.proyect.msreserveepapplication.model.TerraceModel;
import com.proyect.msreserveepapplication.service.EventService;
import com.proyect.msreserveepapplication.service.ClientService;
import com.proyect.msreserveepapplication.service.TerraceService;
import com.proyect.msreserveepapplication.service.StateEventTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TerraceService terraceService;

    @Autowired
    private StateEventTypeService stateEventTypeService;

    @Autowired
    public EventController(EventService eventService, ClientService clientService,
                           TerraceService terraceService, StateEventTypeService stateEventTypeService) {
        this.eventService = eventService;
        this.clientService = clientService;
        this.terraceService = terraceService;
        this.stateEventTypeService = stateEventTypeService;
    }

    @GetMapping()
    public ResponseEntity<Page<EventModel>> findAllAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<EventModel> asociates = eventService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(asociates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<EventModel> event = eventService.findById(id);
        return ResponseEntity.ok(event.get());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<EventModel>> findByClientId(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Integer clientId
    ){
        Page<EventModel> events = eventService.findByClientId(clientId, PageRequest.of(page, size));
        return ResponseEntity.ok(events);
    }

    @GetMapping("terrace/{terraceId}")
    public ResponseEntity<Page<EventModel>> findByTerraceId(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Integer terraceId
    ){
        Page<EventModel> events = eventService.findByTerraceId(terraceId, PageRequest.of(page, size));
        return ResponseEntity.ok(events);
    }

    @PostMapping()
    public ResponseEntity<EventModel> save(@RequestBody EventModel model) {

        if( !clientService.findById( model.getClientModel().getId() ).isPresent() ) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<TerraceModel> terraceSelected = terraceService.findById( model.getTerraceModel().getId() );
        if( !terraceSelected.isPresent() ) {
            return ResponseEntity.badRequest().body(null);
        }

        if( !stateEventTypeService.findById( model.getStateEventType().getId() ).isPresent() ) {
            return ResponseEntity.badRequest().body(null);
        }

        if(model.getSizePeople() > terraceSelected.get().getMaxSize()){
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(eventService.saveEvent(model));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<EventModel> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }
}
