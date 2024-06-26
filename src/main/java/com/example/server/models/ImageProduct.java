package com.example.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "image_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageProduct {
    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    @Column(name = "image_name")
    private String imageName;

    private String describtion;

    private String imageUrl;
}
