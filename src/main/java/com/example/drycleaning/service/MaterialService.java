package com.example.drycleaning.service;

import com.example.drycleaning.model.Material;
import com.example.drycleaning.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository repo;

    public List<Material> getAll() {
        return repo.findAll();
    }

    public Material getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Материал не найден"));
    }

    public Material save(Material material) {
        return repo.save(material);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
