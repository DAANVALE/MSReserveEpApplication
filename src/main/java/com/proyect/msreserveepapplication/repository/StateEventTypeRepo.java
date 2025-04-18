package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.ServiceModel;
import com.proyect.msreserveepapplication.model.StateEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateEventTypeRepo extends JpaRepository<StateEventType, Integer> {
    Page<StateEventType> findByKilled(Byte killed, Pageable pageable);
    Page<StateEventType> findAll(Pageable pageable);
    Optional<StateEventType> findById(Integer id);
}
