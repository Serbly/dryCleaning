package com.example.drycleaning.service;

import com.example.drycleaning.model.StaffRate;
import com.example.drycleaning.repository.StaffRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffRateService {
    private final StaffRateRepository repo;

    public List<StaffRate> getAll() {
        return repo.findAll();
    }

    public StaffRate getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Ставка не найдена"));
    }

    public StaffRate save(StaffRate rate) {
        return repo.save(rate);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
