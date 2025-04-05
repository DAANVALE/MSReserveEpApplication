package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.StateEventType;
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

    public Page<StateEventType> getAllStateEventTypes(Pageable pageable){
        return stateEventTypeRepo.findByKilled((byte) 0, pageable);
    }

    public Optional<StateEventType> getStateEventTypeById(Integer id){
        return stateEventTypeRepo.findById(id);
    }

    public StateEventType saveStateEventType(StateEventType stateEventTypeModel){
        return stateEventTypeRepo.save(stateEventTypeModel);
    }

    public StateEventType setEventKilled(StateEventType stateEventTypeModel){
        stateEventTypeModel.setKilled((byte)1);
        return stateEventTypeRepo.save(stateEventTypeModel);
    }

    // TODO: prov we dont gonna need it, in the controller could make the validations also!
    public StateEventType setEventKilledById(Integer eventTypeId){
        Optional<StateEventType> stateEventTypeModel = stateEventTypeRepo.findById(eventTypeId);
        StateEventType eventTypeToKill = stateEventTypeModel.get();

        eventTypeToKill.setKilled((byte)1);

        return setEventKilled(eventTypeToKill);
    }
}
