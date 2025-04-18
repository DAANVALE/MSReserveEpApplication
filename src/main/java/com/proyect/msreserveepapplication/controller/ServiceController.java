package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping()
    public ResponseEntity<Page<ServiceModel>> findAll
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<ServiceModel> serviceModels = serviceService.findAll(PageRequest.of(page, size));

        return ResponseEntity.ok(serviceModels);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServiceModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<ServiceModel> service = serviceService.findById(id);
        return ResponseEntity.ok(service.get());
    }

    @PostMapping()
    public ResponseEntity<ServiceModel> save(@RequestBody ServiceModel model) {
        return ResponseEntity.ok(serviceService.saveService(model));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ServiceModel> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceService.deleteService(id));
    }
}
