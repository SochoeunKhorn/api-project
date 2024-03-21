package com.example.server.services;

import com.example.server.models.UnitTypes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UnitTypesService {


    Page<UnitTypes> getAllUnitTypes(int page,int size);

    List<UnitTypes> findAll();

    List<UnitTypes> findByStatus(String status);

    UnitTypes findById(Integer id);

    void create(UnitTypes req);

    void delete(UnitTypes req);

    void deleteId(Integer id);

}
