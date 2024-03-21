package com.example.server.repo;

import com.example.server.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByNameEN(String name);
    List<Category> findAllByStatus(String status);
}
