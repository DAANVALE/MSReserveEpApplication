package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.repository.AsociateServiceRepo;
import com.proyect.msreserveepapplication.repository.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceService {
    
    @Autowired
    private final ServiceRepo serviceRepo;

    @Autowired
    private AsociateServiceRepo asociateServiceRepo;

    @Autowired
    public ServiceService(ServiceRepo serviceService) {
        this.serviceRepo = serviceService;
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
        return serviceRepo.findByAsociateService(asociateServiceModel, pageable);
    }

    public ServiceModel deleteService(Integer serviceId){

        Optional<ServiceModel> serviceModel = serviceRepo.findById(serviceId);

        ServiceModel serviceModelToKill = serviceModel.get();
        serviceModelToKill.setKilled((byte)1);

        if(serviceModel.isPresent()){
            serviceRepo.save(serviceModelToKill);

            // TODO: Delete and set Kill every else

        }else{
            throw new ResourceNotFoundException("Client not found with id " + serviceId);
        }

        return serviceModelToKill;
    }
}
