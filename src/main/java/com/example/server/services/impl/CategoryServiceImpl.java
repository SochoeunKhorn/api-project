package com.example.server.services.impl;

import com.example.server.exceptons.WebException;
import com.example.server.models.Category;
import com.example.server.repo.CategoryRepository;
import com.example.server.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    // constructor injection
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllByStatus() {
        return categoryRepository.findAllByStatus("ACT");
    }


    @Override
    public Category getCategoryById(Integer id) throws WebException{
        var checkExistingId = categoryRepository.findById(id);
        log.info("{}",checkExistingId);
        if(checkExistingId.isPresent()){
            return categoryRepository.findById(id).orElse(null);
        }
        else {
            throw new WebException("ID Not Found","ID Not Found","Err-002");
        }
    }

    @Override
    public void create(Category category) throws WebException {
        var checkCategoryName = categoryRepository.findByNameEN(category.getNameEN());
        if(checkCategoryName.isPresent()){
            throw new WebException("Category Name already exist", "Category Name already exist", "Err-001");
        }else {
            category.setId(0);
            category.setStatus("ACT");
            categoryRepository.save(category);
        }

    }

    @Override
    public void update(Category category) throws WebException {
        var findId = categoryRepository.findById(category.getId());
        if(findId.isEmpty()){
            throw  new WebException("Category Name already exist", "Category Name already exist", "Err-001");
        }
        category.setStatus("ACT");
        categoryRepository.save(category);

    }

    @Override
    public void delete(Category category) throws WebException {
        var findId = categoryRepository.findById(category.getId());
        if(findId.isEmpty()){
            throw  new WebException("Category Name already exist", "Category Name already exist", "Err-001");
        }
        category.setStatus("DEL");
        categoryRepository.save(category);
    }

}
