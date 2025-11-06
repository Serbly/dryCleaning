package com.example.drycleaning.service;

import com.example.drycleaning.model.User;
import com.example.drycleaning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
