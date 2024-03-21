package com.example.server.controller;

import com.example.server.exceptons.WebException;
import com.example.server.models.Product;
import com.example.server.models.request.PaginationRequest;
import com.example.server.models.response.BaseResponse;
import com.example.server.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:5173/")
public class ProductController {
    private final ProductService productService;
    private BaseResponse baseResponse;

    @GetMapping("/api/products")
    public ResponseEntity<Object> getAllProduct(){
        log.info("Intercept get all products");
        baseResponse = new BaseResponse();
        try {
            var list = productService.getAllProducts();
            baseResponse.getSuccess(list);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (Throwable e){
            log.info("While get error get all products ",e);
            baseResponse.setCode("500");
            baseResponse.setMessage("Get All products  Unsuccessful");
            baseResponse.setMessageKh("បរាជ័យក្នុងការទាញយក products ទាំងអស់");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

    }

    @GetMapping("/api/products/pagination")
    public ResponseEntity<Object> getProductsPagination(@RequestBody PaginationRequest req){
        log.info("Intercept get products pagination");
        baseResponse = new BaseResponse();
        try {
            var list = productService.getProductsByStatus(req);
            log.info("Intercept get products pagination {}",list);
            baseResponse.getSuccess(list);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (Throwable e){
            log.info("While get error get all products ",e);
            baseResponse.setCode("500");
            baseResponse.setMessage("Get All products  Unsuccessful");
            baseResponse.setMessageKh("បរាជ័យក្នុងការទាញយក products ទាំងអស់");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id){
        log.info("Intercept get products by id {}",id);
        baseResponse = new BaseResponse();
        try {
            var list = productService.getProductById(id);
            baseResponse.getSuccess(list);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (WebException e){
            log.info("While get error products by id ",e);
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/products/create")
    public ResponseEntity<Object> create(@RequestBody Product req){
        log.info("Intercept create products {}",req);
        baseResponse = new BaseResponse();
        try {
            productService.create(req);
            baseResponse.getSuccess(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (WebException e){
            log.info("While get error products by id ",e);
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/products/update")
    public ResponseEntity<Object> update(@RequestBody Product req){
        log.info("Intercept update products {}",req);
        baseResponse = new BaseResponse();
        try {
            productService.update(req);
            baseResponse.updateSuccess(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (WebException e){
            log.info("While get error update products fail",e);
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/products/delete")
    public ResponseEntity<Object> delete(@RequestBody Product req){
        log.info("Intercept delete products {}",req);
        baseResponse = new BaseResponse();
        try {
            productService.delete(req);
            baseResponse.deleteSuccess(req);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (WebException e){
            log.info("While get error update products fail",e);
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }
}
