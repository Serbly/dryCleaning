package com.example.drycleaning.service;

import com.example.drycleaning.model.Employee;
import com.example.drycleaning.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repo;

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    }

    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
