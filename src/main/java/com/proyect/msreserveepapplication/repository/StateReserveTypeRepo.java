package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.StateEventType;
import com.proyect.msreserveepapplication.model.StateReserveType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateReserveTypeRepo extends JpaRepository<StateReserveType, Integer> {
    Page<StateReserveType> findByKilled(Byte killed, Pageable pageable);
    Optional<StateReserveType> findById(Integer id);
}
