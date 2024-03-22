package com.example.server.services;

import com.example.server.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    List<Student> getAllStudentByStatus(String status);

    Student getById(Integer id);

    void create(Student request);

    void update(Student request);

    void delete(Student request);
}
