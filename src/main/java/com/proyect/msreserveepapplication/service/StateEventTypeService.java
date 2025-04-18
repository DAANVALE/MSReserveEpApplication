package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.StateEventType;
import com.proyect.msreserveepapplication.model.StateReserveType;
import com.proyect.msreserveepapplication.repository.StateEventTypeRepo;
import jdk.jfr.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateEventTypeService {
    
    @Autowired
    private final StateEventTypeRepo stateEventTypeRepo;

    @Autowired
    public StateEventTypeService(StateEventTypeRepo stateEventTypeRepo) {
        this.stateEventTypeRepo = stateEventTypeRepo;
    }

    public Page<StateEventType> findAll(Pageable pageable){
        return stateEventTypeRepo.findAll(pageable);
    }

    public Page<StateEventType> findAllActive(Pageable pageable){
        return stateEventTypeRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<StateEventType> findById(Integer id){
        return stateEventTypeRepo.findById(id);
    }

    public StateEventType save(StateEventType stateEventTypeModel){
        return stateEventTypeRepo.save(stateEventTypeModel);
    }

    public StateEventType setEventKilled(StateEventType stateEventTypeModel){
        stateEventTypeModel.setKilled((byte)1);
        return stateEventTypeRepo.save(stateEventTypeModel);
    }

    // TODO: prov we dont gonna need it, in the controller could make the validations also!
    public StateEventType deleteStateEventType(Integer eventTypeId){
        Optional<StateEventType> eventTypeModel = stateEventTypeRepo.findById(eventTypeId);

        if(eventTypeModel.isPresent()){
            return setEventKilled(eventTypeModel.get());
        }else{
            throw new IllegalStateException("Event type not found");
        }
    }
}
