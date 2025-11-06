package com.example.drycleaning.service;

import com.example.drycleaning.model.Role;
import com.example.drycleaning.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public Role getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Role> getAll() {
        return repository.findAll();
    }

    public void create(Role item) {
        Role role = Role.builder()
                .roleName(item.getRoleName())
                .build();
        repository.save(role);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
