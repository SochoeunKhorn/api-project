package com.example.server.services.impl;

import com.example.server.constants.Constants;
import com.example.server.exceptons.WebException;
import com.example.server.models.Product;
import com.example.server.models.request.PaginationRequest;
import com.example.server.repo.CategoryRepository;
import com.example.server.repo.ProductRepository;
import com.example.server.services.CategoryService;
import com.example.server.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    @Override
    public List<Product> getAllProducts() throws WebException {

        return productRepository.findAll();

    }

    @Override
    public List<Product> getProductsByStatus(PaginationRequest req) throws WebException {
        Pageable paging = PageRequest.of(req.getPage()-1,req.getLimit());
        return productRepository.findByStatusOrderById(req.getStatus(),paging).getContent();
    }

    @Override
    public Product getProductById(Integer id) throws WebException  {
        var product = productRepository.findById(id);
        log.info("Find Product {} ",product);
        if(product.isEmpty()){
            throw  new WebException("Product Not Found!","Product Not Found!", Constants.NOT_FOUND);
        }
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Product req) throws WebException {
        // check category exist or not
        var category = categoryRepository.findById(req.getCategory().getId());
        if(category.isEmpty()){
            throw new WebException("Category Not Found","Category Not Found",Constants.NOT_FOUND);
        }

        // check product required
        if(null == req.getCode() || "".equals(req.getBarcode()) || "".equals(req.getName())){
            throw new WebException("Name,Code,BarCode is required","Name,Code,BarCode is required",Constants.REQUIRED);
        }

        // check duplicate item
        var code = productRepository.findByCode(req.getCode());
        if(code.isPresent()){
            throw new WebException("Code is existing","Code is existing",Constants.EXISTING);
        }

        var barCode = productRepository.findByBarcode(req.getBarcode());
        if(barCode.isPresent()){
            throw new WebException("BarCode is existing","BarCode is existing",Constants.EXISTING);
        }

        req.setStatus(Constants.STATUS_ACTIVE);
        req.setId(0);
        productRepository.save(req);
    }

    @Override
    public void update(Product req) throws WebException {
        // check product
        var product = productRepository.findById(req.getId());
        if(product.isEmpty()){
            throw new WebException("Product Not Found","Product Not Found",Constants.NOT_FOUND);
        }
        // check category exist or not
        var category = categoryRepository.findById(req.getCategory().getId());
        if(category.isEmpty()){
            throw new WebException("Category Not Found","Category Not Found",Constants.NOT_FOUND);
        }

        var code = productRepository.findByCode(req.getCode());
        if(code.isPresent()){
            if(!code.get().getCode().equals(product.get().getCode())){
                throw new WebException("Code is existing","Code is existing",Constants.EXISTING);
            }
        }

        var barCode = productRepository.findByBarcode(req.getBarcode());
        if(barCode.isPresent()){
            if(!barCode.get().getBarcode().equals(product.get().getBarcode())){
                throw new WebException("BarCode is existing","BarCode is existing",Constants.EXISTING);
            }
        }
        productRepository.save(req);
    }

    @Override
    public void delete(Product req) throws WebException {
        var product = productRepository.findById(req.getId());
        if(product.isEmpty()){
            throw new WebException("Product Not Found","Product Not Found",Constants.NOT_FOUND);
        }
        req.setStatus(Constants.STATUS_DELETE);

        productRepository.save(req);
    }

}
