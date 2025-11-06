package com.example.drycleaning.service;

import com.example.drycleaning.model.Supply;
import com.example.drycleaning.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository repo;

    public List<Supply> getAll() {
        return repo.findAll();
    }

    public Supply getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Поставка не найдена"));
    }

    public Supply save(Supply supply) {
        return repo.save(supply);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
