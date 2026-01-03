package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.AsociateTerraceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsociateTerraceRepo extends JpaRepository<AsociateTerraceModel, Integer> {
    Page<AsociateTerraceModel> findByKilled(Byte killed, Pageable pageable);
    Optional<AsociateTerraceModel> findById(Integer id);
    Optional<AsociateTerraceModel> findByIdUser(Integer idUser);
}
