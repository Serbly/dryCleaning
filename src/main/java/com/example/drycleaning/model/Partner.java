package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "partners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "director_fullname", columnDefinition = "VARCHAR(255)")
    private String directorFullname;

    @Column(columnDefinition = "VARCHAR(50)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "VARCHAR(20)")
    private String inn;

    private Short rating;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
