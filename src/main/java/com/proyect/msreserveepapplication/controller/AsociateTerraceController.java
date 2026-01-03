package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.service.AsociateTerraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/asociate-terrace")
public class AsociateTerraceController {

    @Autowired
    AsociateTerraceService asociateTerraceService;

    @Autowired
    public AsociateTerraceController(AsociateTerraceService asociateTerraceService) {
        this.asociateTerraceService = asociateTerraceService;
    }

    @GetMapping()
    public ResponseEntity<Page<AsociateTerraceModel>> findAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<AsociateTerraceModel> asociateTerraceModels = asociateTerraceService.findAllByActive(PageRequest.of(page, size));

        return ResponseEntity.ok(asociateTerraceModels);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<AsociateTerraceModel>> findAllAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<AsociateTerraceModel> asociates = asociateTerraceService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(asociates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsociateTerraceModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<AsociateTerraceModel> associate = asociateTerraceService.findByIdUser(id);
        return ResponseEntity.ok(associate.orElse(null));
    }

    @GetMapping("/user/{id}")
    public  ResponseEntity<AsociateTerraceModel> findByUserId(
            @RequestParam Integer id
    ){
        Optional<AsociateTerraceModel> associate = asociateTerraceService.findById(id);
        return ResponseEntity.ok(associate.orElse(null));
    }

    @PostMapping()
    public ResponseEntity<AsociateTerraceModel> save(@RequestBody AsociateTerraceModel model) {
        return ResponseEntity.ok(asociateTerraceService.saveAsociateTerrace(model));
    }

    @PostMapping("/delete")
    public ResponseEntity<AsociateTerraceModel> delete(@RequestBody AsociateTerraceModel model) {
        return ResponseEntity.ok(asociateTerraceService.deleteAsociateService(model.getId()));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<AsociateTerraceModel> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(asociateTerraceService.deleteAsociateService(id));
    }
}
