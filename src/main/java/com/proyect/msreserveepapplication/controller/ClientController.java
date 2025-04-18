package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.ClientModel;
import com.proyect.msreserveepapplication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<Page<ClientModel>> findAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<ClientModel> clientModels = clientService.findAllByActive(PageRequest.of(page, size));

        return ResponseEntity.ok(clientModels);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ClientModel>> findAllAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<ClientModel> asociates = clientService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(asociates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<ClientModel> asociate = clientService.findById(id);
        return ResponseEntity.ok(asociate.orElse(null));
    }

    @PostMapping()
    public ResponseEntity<ClientModel> save(@RequestBody ClientModel model) {
        return ResponseEntity.ok(clientService.saveClient(model));
    }

    @PostMapping("/delete")
    public ResponseEntity<ClientModel> delete(@RequestBody ClientModel model) {
        return ResponseEntity.ok(clientService.deleteClient(model.getId()));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ClientModel> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }
}
