package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.model.TerraceModel;
import com.proyect.msreserveepapplication.repository.AsociateTerraceRepo;
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

    private AsociateTerraceRepo asociateTerraceRepo;

    @Autowired
    public TerraceService(TerraceRepo terraceService) {
        this.terraceRepo = terraceService;
    }

    public Page<TerraceModel> findAll(Pageable pageable) {
        return terraceRepo.findAll(pageable);
    }

    public Page<TerraceModel> findAllActive(Pageable pageable) {
        return terraceRepo.findByKilled((byte)0, pageable);
    }

    public Optional<TerraceModel> findById(Integer id){
        return terraceRepo.findById(id);
    }

    public TerraceModel saveService(TerraceModel terraceModel){
        return terraceRepo.save(terraceModel);
    }

    public Page<TerraceModel> findByAsociateId(Integer asociateId, Pageable pageable){
        Optional<AsociateTerraceModel> asociate = asociateTerraceRepo.findById(asociateId);
        return asociate.map(asociateModel -> findByAsociate(asociateModel, pageable)).orElse(null);
    }

    public Page<TerraceModel> findByAsociate(AsociateTerraceModel asociateTerraceModel, Pageable pageable){
        return terraceRepo.findByAsociateTerrace(asociateTerraceModel, pageable);
    }

    public TerraceModel killTerrace(TerraceModel terraceModel){
        terraceModel.setKilled((byte)1);
        return terraceRepo.save(terraceModel);
    }

    public TerraceModel killTerrace(Integer id){

        Optional<TerraceModel> terrace = terraceRepo.findById(id);
        if(terrace.isPresent()){
            return killTerrace(terrace.get());
        }else{
            throw new RuntimeException("No such terrace");
        }
    }
}
