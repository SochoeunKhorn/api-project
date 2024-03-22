package com.example.server.repo;

import com.example.server.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByEmail(String email);

    List<Student> findByStatus(String status);
}
