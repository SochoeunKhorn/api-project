package com.example.server.controller;

import com.example.server.models.UnitTypes;
import com.example.server.models.response.BaseResponse;
import com.example.server.services.UnitTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class UnitTypesController {
    private final UnitTypesService service;
    private BaseResponse baseResponse;

    @GetMapping("/api/getAll")
    public ResponseEntity<Page<UnitTypes>> getAllPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok().body(service.getAllUnitTypes(page, size));
    }

    @GetMapping("/api/unitTypes")
    public ResponseEntity<Object> getAllUnitTypes(){
        baseResponse = new BaseResponse();
        var list = service.findAll();
        baseResponse.getSuccess(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/api/unitTypes/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id){
        baseResponse = new BaseResponse();
        var category = service.findById(id);
        baseResponse.getSuccess(category);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @GetMapping("/api/unitTypes/status")
    public ResponseEntity<Object> getAllByStatus(){
        baseResponse = new BaseResponse();
        var listByStatus = service.findByStatus("ACT");
        baseResponse.getSuccess(listByStatus);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PostMapping("/api/unitTypes/create")
    public ResponseEntity<Object> create(@RequestBody UnitTypes req){
        baseResponse = new BaseResponse();
        service.create(req);
        baseResponse.createSuccess("");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/api/unitTypes/update")
    public ResponseEntity<Object> update(@RequestBody UnitTypes req){
        baseResponse = new BaseResponse();
        service.create(req);
        baseResponse.updateSuccess(req);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/api/unitTypes/delete")
    public ResponseEntity<Object> delete(@RequestBody UnitTypes req){
        baseResponse = new BaseResponse();
        service.delete(req);
        baseResponse.deleteSuccess(req);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/api/unitTypes/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id){
        baseResponse = new BaseResponse();
        service.deleteId(id);
        baseResponse.deleteSuccess(service.findById(id));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
