package com.example.drycleaning.service;

import com.example.drycleaning.model.OrderService;
import com.example.drycleaning.repository.OrderServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceService {

    private final OrderServiceRepository repo;

    public List<OrderService> getAll() {
        return repo.findAll();
    }

    public OrderService getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись услуги в заказе не найдена"));
    }

    public OrderService save(OrderService entity) {
        return repo.save(entity);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
