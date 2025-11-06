package com.example.drycleaning.repository;

import com.example.drycleaning.model.CostCalculation;
import com.example.drycleaning.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostCalculationRepository extends JpaRepository<CostCalculation, Integer> {
    Optional<CostCalculation> findByService(ServiceEntity service);
}
