package com.example.drycleaning.service;

import com.example.drycleaning.model.Qualification;
import com.example.drycleaning.repository.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationService {
    private final QualificationRepository repo;

    public List<Qualification> getAll() {
        return repo.findAll();
    }

    public Qualification getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Квалификация не найдена"));
    }

    public Qualification save(Qualification qualification) {
        return repo.save(qualification);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
