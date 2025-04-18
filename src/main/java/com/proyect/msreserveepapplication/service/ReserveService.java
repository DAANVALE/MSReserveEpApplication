package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.EventModel;
import com.proyect.msreserveepapplication.model.ReserveModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.repository.EventRepo;
import com.proyect.msreserveepapplication.repository.ReserveRepo;
import com.proyect.msreserveepapplication.repository.ServiceRepo;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReserveService {
    
    @Autowired
    private final ReserveRepo reserveRepo;

    private EventRepo eventModel;

    private ServiceRepo serviceRepo;

    @Autowired
    public ReserveService(ReserveRepo reserveRepo) {
        this.reserveRepo = reserveRepo;
    }

    public Page<ReserveModel> findAll(Pageable pageable){
        return reserveRepo.findAll(pageable);
    }

    public Optional<ReserveModel> findById(Integer id){
        return reserveRepo.findById(id);
    }

    public ReserveModel saveReserve(ReserveModel reserveModel){
        return reserveRepo.save(reserveModel);
    }

    public Page<ReserveModel> findByEventModelId(Integer eventModelId, Pageable pageable){
        Optional<EventModel> event = eventModel.findById(eventModelId);
        return event.map(eventModel -> findByEventModel(eventModel, pageable)).orElse(null);
    }

    public Page<ReserveModel> findByEventModel(EventModel event, Pageable pageable){
        return reserveRepo.findByEventModel(event, pageable);
    }

    public Page<ReserveModel> findByServiceId(Integer serviceId, Pageable pageable){
        Optional<ServiceModel> service = serviceRepo.findById(serviceId);
        return service.map(serviceModel -> findByService(serviceModel, pageable)).orElse(null);
    }

    public Page<ReserveModel> findByService(ServiceModel service, Pageable pageable){
        return reserveRepo.findByServiceModel(service, pageable);
    }

    public ReserveModel deleteReserve(ReserveModel reserveModel){
        reserveRepo.delete(reserveModel);
        return reserveModel;
    }

    public ReserveModel deleteReserveById(Integer id){
        ReserveModel reserveModel = reserveRepo.findById(id).orElse(null);
        deleteReserve(reserveModel);
        return reserveModel;
    }

}
