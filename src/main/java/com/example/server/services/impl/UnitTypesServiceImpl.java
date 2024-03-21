package com.example.server.services.impl;

import com.example.server.constants.Constants;
import com.example.server.models.UnitTypes;
import com.example.server.repo.UnitTypesRepository;
import com.example.server.services.UnitTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitTypesServiceImpl implements UnitTypesService {
    private final UnitTypesRepository unitTypesRepository;
    @Override
    public Page<UnitTypes> getAllUnitTypes(int page, int size) {
        return unitTypesRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
    }
    @Override
    public List<UnitTypes> findAll() {
        return unitTypesRepository.findAll();
    }

    @Override
    public List<UnitTypes> findByStatus(String status) {
        return unitTypesRepository.findAllByStatus(status);
    }

    @Override
    public UnitTypes findById(Integer id) {
        var existID = unitTypesRepository.findById(id);
        if(existID.isPresent()){
            return unitTypesRepository.findById(id).orElse(null);
        }
        else {
            return null;
        }
    }

    @Override
    public void create(UnitTypes req) {
        req.setId(0);
        unitTypesRepository.save(req);
    }

    @Override
    public void delete(UnitTypes req) {

        unitTypesRepository.delete(req);
    }

    @Override
    public void deleteId(Integer id) {
        var find = unitTypesRepository.findById(id);
        if(find.isPresent()){
            find.get().setStatus(Constants.STATUS_DISABLE);
        }
    }
}
