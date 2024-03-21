package com.example.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unit_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    private double qty;

    @Column(length = 3)
    private String status;
}
