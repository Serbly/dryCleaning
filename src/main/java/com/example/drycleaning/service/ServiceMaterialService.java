package com.example.drycleaning.service;

import com.example.drycleaning.model.ServiceMaterial;
import com.example.drycleaning.repository.ServiceMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceMaterialService {

    private final ServiceMaterialRepository repo;

    public List<ServiceMaterial> getAll() {
        return repo.findAll();
    }

    public ServiceMaterial getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Материал услуги не найден"));
    }

    public ServiceMaterial save(ServiceMaterial entity) {
        return repo.save(entity);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
