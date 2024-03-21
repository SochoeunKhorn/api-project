package com.example.server.repo;

import com.example.server.models.UnitTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitTypesRepository extends JpaRepository<UnitTypes,Integer> {
    List<UnitTypes> findAllByStatus(String status);
}
