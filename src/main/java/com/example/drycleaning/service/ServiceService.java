package com.example.drycleaning.service;

import com.example.drycleaning.model.ServiceEntity;
import com.example.drycleaning.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository repo;

    public List<ServiceEntity> getAll() {
        return repo.findAll();
    }

    public ServiceEntity getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Услуга не найдена"));
    }

    public ServiceEntity save(ServiceEntity service) {
        return repo.save(service);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
