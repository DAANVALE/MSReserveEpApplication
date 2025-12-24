package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.StateEventType;
import com.proyect.msreserveepapplication.service.StateEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/state-event")
public class StateEventTypeController {

    @Autowired
    StateEventTypeService stateEventTypeService;

    @Autowired
    public StateEventTypeController(StateEventTypeService stateEventTypeService) {
        this.stateEventTypeService = stateEventTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<StateEventType>> findAll
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "12") Integer size
            ) {

        Page<StateEventType> stateEventTypes = stateEventTypeService.findAll(PageRequest.of(page, size));

        return ResponseEntity.ok(stateEventTypes);
    }
    
    @GetMapping()
    public ResponseEntity<Page<StateEventType>> findAllActive
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "12") Integer size
            ) {

        Page<StateEventType> stateEventTypes = stateEventTypeService.findAllActive(PageRequest.of(page, size));

        return ResponseEntity.ok(stateEventTypes);
    }

    @GetMapping("{id}")
    public ResponseEntity<StateEventType> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "12") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<StateEventType> service = stateEventTypeService.findById(id);
        return ResponseEntity.ok(service.get());
    }

    @PostMapping()
    public ResponseEntity<StateEventType> save(@RequestBody StateEventType model) {
        return ResponseEntity.ok(stateEventTypeService.save(model));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<StateEventType> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(stateEventTypeService.deleteStateEventType(id));
    }
}
