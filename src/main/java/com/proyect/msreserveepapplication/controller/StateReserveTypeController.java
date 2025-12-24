package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.StateReserveType;
import com.proyect.msreserveepapplication.service.StateReserveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/state-reserve")
public class StateReserveTypeController {

    @Autowired
    StateReserveTypeService stateReserveTypeService;

    @Autowired
    public StateReserveTypeController(StateReserveTypeService stateReserveTypeService) {
        this.stateReserveTypeService = stateReserveTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<StateReserveType>> findAll
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "12") Integer size
            ) {

        Page<StateReserveType> stateReserveTypes = stateReserveTypeService.findAll(PageRequest.of(page, size));

        return ResponseEntity.ok(stateReserveTypes);
    }
    
    @GetMapping()
    public ResponseEntity<Page<StateReserveType>> findAllActive
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "12") Integer size
            ) {

        Page<StateReserveType> stateReserveTypes = stateReserveTypeService.findAllActive(PageRequest.of(page, size));

        return ResponseEntity.ok(stateReserveTypes);
    }

    @GetMapping("{id}")
    public ResponseEntity<StateReserveType> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<StateReserveType> service = stateReserveTypeService.findById(id);
        return ResponseEntity.ok(service.get());
    }

    @PostMapping()
    public ResponseEntity<StateReserveType> save(@RequestBody StateReserveType model) {
        return ResponseEntity.ok(stateReserveTypeService.save(model));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<StateReserveType> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(stateReserveTypeService.setKilled(id));
    }
}
