package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.repository.AsociateServiceRepo;
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
    private AsociateServiceRepo asociateServiceRepo;

    @Autowired
    public AsociateTerraceService(AsociateTerraceRepo asociateTerraceRepo) {
        this.asociateTerraceRepo = asociateTerraceRepo;
    }

    public Page<AsociateTerraceModel> findAll(Pageable pageable){
        return asociateTerraceRepo.findAll(pageable);
    }

    public Page<AsociateTerraceModel> findAllByActive(Pageable pageable){
        return asociateTerraceRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<AsociateTerraceModel> findById(Integer id){
        return asociateTerraceRepo.findById(id);
    }

    public Optional<AsociateTerraceModel> findByIdUser(Integer id){
        return asociateTerraceRepo.findByIdUser(id);
    }

    public Optional<AsociateTerraceModel> findAsociateTerraceById(Integer id){
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
