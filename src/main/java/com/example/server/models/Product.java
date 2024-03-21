package com.example.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String code;
    private String barcode;
    private String description;
    private double qtyOnHand;
    private String image;

    @Column(length = 1)
    private String stockType;

    @Column(length = 3)
    private String status;

    @ManyToOne
    private Category category;

}
