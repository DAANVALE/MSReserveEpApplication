package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.ReserveModel;
import com.proyect.msreserveepapplication.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping()
    public ResponseEntity<Page<ReserveModel>> findAllAsociates
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size
            ) {

        Page<ReserveModel> asociates = reserveService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(asociates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveModel> findById
            (
                    @RequestParam(defaultValue = "0") Integer page,
                    @RequestParam(defaultValue = "10") Integer size,
                    @PathVariable Integer id
            ) {

        Optional<ReserveModel> reserve = reserveService.findById(id);
        return ResponseEntity.ok(reserve.get());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Page<ReserveModel>> findByReserveService(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Integer eventId
    ){
        Page<ReserveModel> reserves = reserveService.findByEventModelId(eventId, PageRequest.of(page, size));
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<Page<ReserveModel>> findByTerraceId(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Integer serviceId
    ){
        Page<ReserveModel> reserves = reserveService.findByServiceId(serviceId, PageRequest.of(page, size));
        return ResponseEntity.ok(reserves);
    }

    @PostMapping()
    public ResponseEntity<ReserveModel> save(@RequestBody ReserveModel model) {
        return ResponseEntity.ok(reserveService.saveReserve(model));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReserveModel> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(reserveService.deleteReserveById(id));
    }
}
