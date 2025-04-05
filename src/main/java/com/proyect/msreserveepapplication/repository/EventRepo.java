package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.ClientModel;
import com.proyect.msreserveepapplication.model.EventModel;

import com.proyect.msreserveepapplication.model.TerraceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<EventModel, Integer> {
    Page<EventModel> findByKilled(Byte killed, Pageable pageable);

    Page<EventModel> findByClientModel(ClientModel clientModel, Pageable pageable);
    Page<EventModel> findByTerraceModel(TerraceModel terraceModel, Pageable pageable);

    Optional<EventModel> findById(Integer id);
}
