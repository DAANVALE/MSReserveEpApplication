package com.proyect.msreserveepapplication.controller;

import com.proyect.msreserveepapplication.model.EventModel;
import com.proyect.msreserveepapplication.model.ReserveModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.service.EventService;
import com.proyect.msreserveepapplication.service.ReserveService;
import com.proyect.msreserveepapplication.service.ServiceService;
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
    ServiceService serviceService;

    @Autowired
    EventService eventService;

    @Autowired
    public ReserveController(ReserveService reserveService, ServiceService serviceService, EventService eventService) {
        this.reserveService = reserveService;
        this.serviceService = serviceService;
        this.eventService = eventService;
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

        Optional<ServiceModel> serviceSelected = serviceService.findById(model.getServiceModel().getId());
        Optional<EventModel> eventModelSelected = eventService.findById(model.getEventModel().getId());

        if(!serviceSelected.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        if(!eventModelSelected.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        if(model.getSizePeople() > serviceSelected.get().getMaxSize()){
            return ResponseEntity.badRequest().body(null);
        }

        int extraPeople = model.getSizePeople() - serviceSelected.get().getBaseSize();
        if(extraPeople > 0){

            int times10 = extraPeople % 10 > 0 ?
                    (extraPeople / 10) + 1 : extraPeople / 10;

            int basePrice  = serviceSelected.get().getBasePrice();
            int pricePer10 = serviceSelected.get().getPriceAdd10();

            model.setFinalPrice( basePrice + (pricePer10 * times10) );
        }

        return ResponseEntity.ok(reserveService.saveReserve(model));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReserveModel> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(reserveService.deleteReserveById(id));
    }
}
