package com.example.server.repo;

import com.example.server.models.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct,String> {
}
