package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.repository.AsociateServiceRepo;
import com.proyect.msreserveepapplication.repository.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {
    
    @Autowired
    private final ServiceRepo serviceRepo;

    @Autowired
    private final AsociateServiceRepo asociateServiceRepo;

    @Autowired
    public ServiceService(ServiceRepo serviceService, AsociateServiceRepo asociateServiceRepo) {
        this.serviceRepo = serviceService;
        this.asociateServiceRepo = asociateServiceRepo;
    }

    public Page<ServiceModel> findAll(Pageable pageable) {
        return serviceRepo.findByKilled((byte)0, pageable);
    }

    public Optional<ServiceModel> findById(Integer id){
        return serviceRepo.findById(id);
    }

    public ServiceModel saveService(ServiceModel serviceModel){
        return serviceRepo.save(serviceModel);
    }

    public Page<ServiceModel> findByAsociateId(Integer asociateId, Pageable pageable){
        Optional<AsociateServiceModel> asociate = asociateServiceRepo.findById(asociateId);
        return asociate.map(asociateModel -> findByAsociate(asociateModel, pageable)).orElse(null);
    }

    public Page<ServiceModel> findByAsociate(AsociateServiceModel asociateServiceModel, Pageable pageable){
        return serviceRepo.findByAsociate(asociateServiceModel, pageable);
    }
}
