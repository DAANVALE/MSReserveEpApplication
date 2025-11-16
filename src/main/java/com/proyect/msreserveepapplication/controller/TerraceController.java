package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.TerraceModel;
import com.proyect.msreserveepapplication.service.TerraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/terrace")
public class TerraceController {

    @Autowired
    TerraceService terraceService;

    @Autowired
    public TerraceController(TerraceService terraceService) {
        this.terraceService = terraceService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TerraceModel>> findAll
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<TerraceModel> terraceModels = terraceService.findAll(PageRequest.of(page, size));

        return ResponseEntity.ok(terraceModels);
    }

    @GetMapping()
    public ResponseEntity<Page<TerraceModel>> findAllActive
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<TerraceModel> terraceModels = terraceService.findAllActive(PageRequest.of(page, size));

        return ResponseEntity.ok(terraceModels);
    }

    @GetMapping("{id}")
    public ResponseEntity<TerraceModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<TerraceModel> service = terraceService.findById(id);
        return ResponseEntity.ok(service.get());
    }

    @PostMapping()
    public ResponseEntity<TerraceModel> save(@RequestBody TerraceModel model) {
        return ResponseEntity.ok(terraceService.saveService(model));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<TerraceModel> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(terraceService.killTerrace(id));
    }
}
