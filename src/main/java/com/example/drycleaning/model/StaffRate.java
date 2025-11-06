package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "staff_rates")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "qualification_id", nullable = false)
    private Qualification qualification;

    @Column(name = "hourly_rate", nullable = false)
    private Double hourlyRate;
}
