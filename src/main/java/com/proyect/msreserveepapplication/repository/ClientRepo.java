package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import com.proyect.msreserveepapplication.model.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<ClientModel, Integer> {
    Page<ClientModel> findByKilled(Byte killed, Pageable pageable);
    Optional<ClientModel> findById(Integer id);
}
