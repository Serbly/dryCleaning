package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name", columnDefinition = "VARCHAR(50)")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public String toString() {
        return roleName;
    }
}
