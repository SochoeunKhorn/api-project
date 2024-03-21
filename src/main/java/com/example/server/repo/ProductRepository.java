package com.example.server.repo;

import com.example.server.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByCode(String code);
    Optional<Product> findByBarcode(String barcode);

    Page<Product> findByStatusOrderById(String status, Pageable pageable);

}
