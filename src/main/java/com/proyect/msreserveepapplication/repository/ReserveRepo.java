package com.proyect.msreserveepapplication.repository;

import com.proyect.msreserveepapplication.model.EventModel;
import com.proyect.msreserveepapplication.model.ReserveModel;
import com.proyect.msreserveepapplication.model.ServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveRepo extends JpaRepository<ReserveModel, Integer> {
    Page<ReserveModel> findByEventModel(EventModel event, Pageable pageable);
    Page<ReserveModel> findByServiceModel(ServiceModel service, Pageable pageable);
    Optional<ReserveModel> findById(Integer id);
}
