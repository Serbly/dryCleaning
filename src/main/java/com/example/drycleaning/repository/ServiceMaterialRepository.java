package com.example.drycleaning.repository;

import com.example.drycleaning.model.ServiceMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMaterialRepository extends JpaRepository<ServiceMaterial, Integer> {
}
