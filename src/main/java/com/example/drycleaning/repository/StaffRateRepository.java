package com.example.drycleaning.repository;

import com.example.drycleaning.model.StaffRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRateRepository extends JpaRepository<StaffRate, Integer> {
}
