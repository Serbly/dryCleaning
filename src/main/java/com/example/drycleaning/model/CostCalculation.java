package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "cost_calculations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calc_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @Column(name = "calc_date")
    private LocalDateTime calcDate;

    @Column(name = "materials_cost")
    private Double materialsCost;

    @Column(name = "labor_cost")
    private Double laborCost;

    @Column(name = "total_cost")
    private Double totalCost;
}
