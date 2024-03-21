package com.example.server.services;


import com.example.server.exceptons.WebException;
import com.example.server.models.Product;
import com.example.server.models.request.PaginationRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts() throws WebException;

    List<Product> getProductsByStatus(PaginationRequest req) throws WebException;
    Product getProductById(Integer id) throws WebException;

    void create(Product req) throws WebException;

    void update(Product re) throws WebException;

    void delete(Product req) throws WebException;
}
