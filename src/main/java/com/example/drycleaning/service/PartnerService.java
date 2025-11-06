package com.example.drycleaning.service;

import com.example.drycleaning.model.Partner;
import com.example.drycleaning.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {
    private final PartnerRepository repo;

    public List<Partner> getAll() {
        return repo.findAll();
    }

    public Partner getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Партнёр не найден"));
    }

    public Partner save(Partner partner) {
        return repo.save(partner);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
