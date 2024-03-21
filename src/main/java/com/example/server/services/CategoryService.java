package com.example.server.services;

import com.example.server.exceptons.WebException;
import com.example.server.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getAllByStatus();
    Category getCategoryById(Integer id) throws WebException;

    void create(Category category) throws WebException;
    void update(Category category) throws WebException;

    void delete(Category category) throws WebException;

    void deleteById(Integer id) throws WebException;
}
