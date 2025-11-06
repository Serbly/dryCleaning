package com.example.drycleaning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "contact_person", columnDefinition = "VARCHAR(255)")
    private String contactPerson;

    @Column(columnDefinition = "VARCHAR(50)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(columnDefinition = "text")
    private String address;

    @Column(columnDefinition = "VARCHAR(20)")
    private String inn;

    private Short rating;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Supply> supplies;
}
