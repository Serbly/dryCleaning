package com.example.drycleaning.service;

import com.example.drycleaning.model.CostCalculation;
import com.example.drycleaning.repository.CostCalculationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostCalculationService {
    private final CostCalculationRepository repo;

    public List<CostCalculation> getAll() {
        return repo.findAll();
    }

    public CostCalculation getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Расчёт не найден"));
    }

    public CostCalculation save(CostCalculation calc) {
        if (calc.getTotalCost() == null) {
            calc.setTotalCost(calculateTotalCost(calc));
        }
        return repo.save(calc);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public double calculateTotalCost(CostCalculation calc) {
        if (calc.getMaterialsCost() == null || calc.getLaborCost() == null) {
            throw new IllegalArgumentException("Отсутствуют данные для расчёта себестоимости");
        }
        return calc.getMaterialsCost() + calc.getLaborCost();
    }
}
