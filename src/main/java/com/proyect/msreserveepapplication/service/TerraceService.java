package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.model.TerraceModel;
import com.proyect.msreserveepapplication.repository.TerraceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TerraceService {
    
    @Autowired
    private final TerraceRepo terraceRepo;

    @Autowired
    public TerraceService(TerraceRepo terraceService) {
        this.terraceRepo = terraceService;
    }

    public Page<TerraceModel> findAll(Pageable pageable) {
        return terraceRepo.findByKilled((byte)0, pageable);
    }

    public Optional<TerraceModel> findById(Integer id){
        return terraceRepo.findById(id);
    }

    public TerraceModel saveService(TerraceModel terraceModel){
        return terraceRepo.save(terraceModel);
    }

    public Page<ServiceModel> findByAsociate(AsociateTerraceModel asociateTerraceModel, Pageable pageable){
        return terraceRepo.findByAsociate(asociateTerraceModel, pageable);
    }
}
