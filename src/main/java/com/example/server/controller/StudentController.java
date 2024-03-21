package com.example.server.controller;

import com.example.server.models.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class StudentController {

    private BaseResponse baseResponse;

    @GetMapping("/api/students/info")
    public ResponseEntity<Object> studentInfo(){
        baseResponse = new BaseResponse();
        baseResponse.getSuccess("data");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
