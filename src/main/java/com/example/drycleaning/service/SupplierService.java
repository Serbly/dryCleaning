package com.example.drycleaning.service;

import com.example.drycleaning.model.Supplier;
import com.example.drycleaning.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository repo;

    public List<Supplier> getAll() {
        return repo.findAll();
    }

    public Supplier getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Поставщик не найден"));
    }

    public Supplier save(Supplier supplier) {
        return repo.save(supplier);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
