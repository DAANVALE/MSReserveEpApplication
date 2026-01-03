package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsociateServiceRepo extends JpaRepository<AsociateServiceModel, Integer> {
    Page<AsociateServiceModel> findByKilled(Byte killed, Pageable pageable);
    Optional<AsociateServiceModel> findById(Integer id);
    Optional<AsociateServiceModel> findByIdUser(Integer idUser);
}
