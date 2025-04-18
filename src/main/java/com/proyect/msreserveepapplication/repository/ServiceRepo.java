package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.AsociateServiceModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceModel, Integer> {
    Page<ServiceModel> findByKilled(Byte killed, Pageable pageable);
    Page<ServiceModel> findByAsociateService(AsociateServiceModel asociateService, Pageable pageable);
    Optional<ServiceModel> findById(Integer id);
}
