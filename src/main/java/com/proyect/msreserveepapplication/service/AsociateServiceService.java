package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import com.proyect.msreserveepapplication.repository.AsociateServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsociateServiceService {
    @Autowired
    private final AsociateServiceRepo asociateServiceRepo;

    @Autowired
    public AsociateServiceService(AsociateServiceRepo asociateServiceRepo) {
        this.asociateServiceRepo = asociateServiceRepo;
    }

    public Page<AsociateServiceModel> findAllByActive(Pageable pageable){
        return asociateServiceRepo.findByKilled((byte) 0, pageable);
    }

    public Page<AsociateServiceModel> findAll(Pageable pageable){
        return asociateServiceRepo.findAll(pageable);
    }

    public Optional<AsociateServiceModel> findById(Integer id){
        return asociateServiceRepo.findById(id);
    }

    public AsociateServiceModel saveAsociateService(AsociateServiceModel asociateServiceModel){
        return asociateServiceRepo.save(asociateServiceModel);
    }

    public AsociateServiceModel deleteAsociateService(Integer id){
        Optional<AsociateServiceModel> asociateServiceModel = asociateServiceRepo.findById(id);
        // Update to killed
        AsociateServiceModel asociateServiceModelToDelete = asociateServiceModel.get();
        asociateServiceModelToDelete.setKilled((byte) 1);

        if(asociateServiceRepo.findById(id).isPresent()){
            asociateServiceRepo.save(asociateServiceModelToDelete);

            // TODO: Delete and set killed

        }else{
            throw new ResourceNotFoundException("Asociate Service Not Found");
        }

        return asociateServiceModelToDelete;
    }

}
