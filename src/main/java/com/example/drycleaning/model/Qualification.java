package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "qualifications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARChAR(100)")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "qualification")
    private List<Employee> employees;

    public String toString() {
        return name;
    }
}
