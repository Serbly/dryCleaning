package com.example.drycleaning.service;

import com.example.drycleaning.model.Order;
import com.example.drycleaning.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Заказ не найден"));
    }

    public Order save(Order order) {
        return repo.save(order);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
