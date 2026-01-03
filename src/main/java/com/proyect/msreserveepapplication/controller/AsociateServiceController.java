package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import com.proyect.msreserveepapplication.service.AsociateServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/asociate-service")
public class AsociateServiceController {

    @Autowired
    AsociateServiceService asociateServiceService;

    @Autowired
    public AsociateServiceController(AsociateServiceService asociateServiceService) {
        this.asociateServiceService = asociateServiceService;
    }

    @GetMapping()
    public ResponseEntity<Page<AsociateServiceModel>> findAllActive
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<AsociateServiceModel> asociateServiceModels = asociateServiceService.findAllByActive(PageRequest.of(page, size));
        return ResponseEntity.ok(asociateServiceModels);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<AsociateServiceModel>> findAllAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<AsociateServiceModel> asociates = asociateServiceService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(asociates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsociateServiceModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<AsociateServiceModel> asociate = asociateServiceService.findById(id);
        return ResponseEntity.ok(asociate.orElse(null));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AsociateServiceModel> findByUserId(
            @RequestParam Integer id
    ){
        Optional<AsociateServiceModel> asociate = asociateServiceService.findByIdUser(id);
        return ResponseEntity.ok(asociate.orElse(null));
    }

    @PostMapping()
    public ResponseEntity<AsociateServiceModel> save(@RequestBody AsociateServiceModel model) {
        return ResponseEntity.ok(asociateServiceService.saveAsociateService(model));
    }

    @PostMapping("/delete")
    public ResponseEntity<AsociateServiceModel> delete(@RequestBody AsociateServiceModel model) {
        return ResponseEntity.ok(asociateServiceService.deleteAsociateService(model.getId()));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<AsociateServiceModel> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(asociateServiceService.deleteAsociateService(id));
    }
}
