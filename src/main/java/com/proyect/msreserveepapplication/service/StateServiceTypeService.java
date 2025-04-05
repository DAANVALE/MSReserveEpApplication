package com.proyect.msreserveepapplication.service;

import com.proyect.msreserveepapplication.model.StateEventType;
import com.proyect.msreserveepapplication.repository.StateEventTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateServiceTypeService {

    @Autowired
    private final StateEventTypeRepo stateEventTypeRepo;

    @Autowired
    public StateServiceTypeService(StateEventTypeRepo stateEventTypeRepo) {
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
}
