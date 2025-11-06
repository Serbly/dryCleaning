package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "services")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "norm_time", nullable = false)
    private Double normTime;

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;

    @OneToMany(mappedBy = "service")
    private List<ServiceMaterial> serviceMaterials;
}
