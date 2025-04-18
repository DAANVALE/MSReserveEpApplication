package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.StateReserveType;
import com.proyect.msreserveepapplication.repository.StateReserveTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.Optional;

@Service
public class StateReserveTypeService {
    
    @Autowired
    private final StateReserveTypeRepo stateReserveTypeRepo;

    @Autowired
    public StateReserveTypeService(StateReserveTypeRepo stateReserveTypeRepo) {
        this.stateReserveTypeRepo = stateReserveTypeRepo;
    }

    public Page<StateReserveType> findAll(Pageable pageable){
        return stateReserveTypeRepo.findAll(pageable);
    }
    public Page<StateReserveType> findAllActive(Pageable pageable){
        return stateReserveTypeRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<StateReserveType> findById(Integer id){
        return stateReserveTypeRepo.findById(id);
    }

    public StateReserveType save(StateReserveType stateReserveTypeModel){
        return stateReserveTypeRepo.save(stateReserveTypeModel);
    }

    public StateReserveType setEventKilled(StateReserveType stateReserveTypeModel){
        stateReserveTypeModel.setKilled((byte)1);
        return stateReserveTypeRepo.save(stateReserveTypeModel);
    }

    // TODO: prov we dont gonna need it, in the controller could make the validations also!
    public StateReserveType setKilled(Integer eventTypeId){
        Optional<StateReserveType> stateReserveTypeModel = stateReserveTypeRepo.findById(eventTypeId);

        if(stateReserveTypeModel.isPresent()){
            return setEventKilled(stateReserveTypeModel.get());
        }else{
            throw new IllegalStateException("Reserve type not found");
        }
    }
}
