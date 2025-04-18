package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.EventModel;
import com.proyect.msreserveepapplication.service.EventService;
import org.apache.coyote.Response;
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
    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
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
        return ResponseEntity.ok(eventService.saveEvent(model));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<EventModel> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.deleteEvent(id));
    }
}
