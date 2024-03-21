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
            baseResponse.setCode("200");
            baseResponse.setMessage("Get All Categories Successful");
            baseResponse.setMessageKh("ទាញយក Category ទាំងអស់");
            baseResponse.setData(list);
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
            baseResponse.setCode("200");
            baseResponse.setMessage("Get Category By Id Successful");
            baseResponse.setMessageKh("ទាញយក Category តាមរយះ ID");
            baseResponse.setData(category);
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
            baseResponse.setMessage("create category successfully");
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
            baseResponse.setMessage("Updated");
            baseResponse.setCode("200");
            baseResponse.setMessageKh("Category Updated");
            categoryService.update(req);
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
            baseResponse.setMessage("Delete");
            baseResponse.setCode("200");
            baseResponse.setMessageKh("Category Deleted");
            categoryService.delete(req);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }catch (WebException e){
            baseResponse.setCode(e.getCode());
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
    }

    @PutMapping("/api/categories/update/{id}")
    public ResponseEntity<Object> updateID(@PathVariable("id") Integer id){
        return new ResponseEntity<>("Update By ID : "+id,HttpStatus.OK);
    }

    @DeleteMapping("/api/categories/delete/{id}")
    public ResponseEntity<Object> deleteID(@PathVariable("id") Integer id) throws WebException{
        baseResponse = new BaseResponse();
        try{
            baseResponse.setMessage("Deleted");
            baseResponse.setMessageKh("Deleted");
            baseResponse.setCode("200");
            baseResponse.setData("Category ID : " + id + "delete successfully");
            categoryService.deleteById(id);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }catch (WebException e){
            baseResponse.setMessage(e.getMessage());
            baseResponse.setMessageKh(e.getMessageKh());
            baseResponse.setCode(e.getCode());
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }

    }
}
