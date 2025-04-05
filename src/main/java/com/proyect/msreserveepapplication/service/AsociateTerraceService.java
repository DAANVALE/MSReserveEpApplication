package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.repository.AsociateTerraceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsociateTerraceService {
    
    @Autowired
    private final AsociateTerraceRepo asociateTerraceRepo;

    @Autowired
    public AsociateTerraceService(AsociateTerraceRepo asociateTerraceRepo) {
        this.asociateTerraceRepo = asociateTerraceRepo;
    }

    public Page<AsociateTerraceModel> getAllAsociateTerraces(Pageable pageable){
        return asociateTerraceRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<AsociateTerraceModel> getAsociateTerraceById(Integer id){
        return asociateTerraceRepo.findById(id);
    }

    public AsociateTerraceModel saveAsociateTerrace(AsociateTerraceModel asociateTerraceModel){
        return asociateTerraceRepo.save(asociateTerraceModel);
    }

    public AsociateTerraceModel deleteAsociateService(Integer id){
        Optional<AsociateTerraceModel> asociateTerraceModel = asociateTerraceRepo.findById(id);
        // Update to killed
        AsociateTerraceModel asociateTerraceModelToKill = asociateTerraceModel.get();
        asociateTerraceModelToKill.setKilled((byte) 1);

        if(asociateTerraceRepo.findById(id).isPresent()){
            asociateTerraceRepo.save(asociateTerraceModelToKill);

            // TODO: Delete and set killed

        }else{
            throw new ResourceNotFoundException("Asociate not Found with id: " + id);
        }

        return asociateTerraceModelToKill;
    }
}
