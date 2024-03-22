package com.example.server.controller;

import com.example.server.models.Student;
import com.example.server.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/api/students")
    public ResponseEntity<Object> getAllStudents(){
        var listStudent = studentService.getAllStudent();
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Integer id){
        var student = studentService.getById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/api/students/create")
    public ResponseEntity<Object> createStudent(@RequestBody Student req){
        studentService.create(req);
        return new ResponseEntity<>(req, HttpStatus.OK);
    }

    @PostMapping("/api/students/update")
    public ResponseEntity<Object> updateStudent(@RequestBody Student req){
        studentService.update(req);
        return new ResponseEntity<>(req, HttpStatus.OK);
    }

    @PostMapping("/api/students/delete")
    public ResponseEntity<Object> deleteStudent(@RequestBody Student req){
        studentService.delete(req);
        return new ResponseEntity<>(req, HttpStatus.OK);
    }
}
