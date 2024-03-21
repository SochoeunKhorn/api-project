package com.example.server.controller;

import com.example.server.exceptons.WebException;
import com.example.server.models.Category;
import com.example.server.models.response.BaseResponse;
import com.example.server.services.CategoryService;
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
public class CategoryController {
    // constructor injection
    private final CategoryService categoryService;
    private BaseResponse baseResponse;

    @GetMapping("/api/categories")
    public ResponseEntity<Object> getAllCategory(){
        log.info("Intercept get all categories");
        baseResponse = new BaseResponse();
        try {
            var list = categoryService.getAllByStatus();
            baseResponse.getSuccess(list);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (Throwable e){
            log.info("While get error get all categories ",e);
            baseResponse.setCode("500");
            baseResponse.setMessage("Get All Categories  Unsuccessful");
            baseResponse.setMessageKh("បរាជ័យក្នុងការទាញយក Category ទាំងអស់");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id){
        log.info("Intercept get  category by id {}",id);
        baseResponse = new BaseResponse();
        try {
            var category = categoryService.getCategoryById(id);
            baseResponse.getSuccess(category);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (WebException e){
            log.info("While get error get category by id  ",e);
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/categories/create")
    public ResponseEntity<Object> createCategory(@RequestBody Category req){
        baseResponse = new BaseResponse();
        try {
            log.info("Intercept Create Successfully");
            categoryService.create(req);
            baseResponse.createSuccess(req);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }catch (WebException e){
            baseResponse = new BaseResponse();
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            baseResponse.setCode(e.getCode());
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
    }

    @PostMapping("/api/categories/update")
    public ResponseEntity<Object> updateCategory(@RequestBody Category req){
        baseResponse = new BaseResponse();
        try {
            log.info("Intercept Update Successfully");
            categoryService.update(req);
            baseResponse.updateSuccess(req);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }catch (WebException e){
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
    }

    @PostMapping("/api/categories/delete")
    public ResponseEntity<Object> deleteCategory(@RequestBody Category req){
        baseResponse = new BaseResponse();
        try {
            log.info("Intercept Update Successfully");
            categoryService.delete(req);
            baseResponse.deleteSuccess(req);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }catch (WebException e){
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
    }

}
