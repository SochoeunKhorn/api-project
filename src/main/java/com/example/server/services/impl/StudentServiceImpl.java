package com.example.server.services.impl;

import com.example.server.models.Student;
import com.example.server.repo.StudentRepository;
import com.example.server.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllStudentByStatus(String status) {
        return studentRepository.findByStatus(status);
    }

    @Override
    public Student getById(Integer id) {
        var checkId = studentRepository.findById(id);
        if(checkId.isEmpty()){
            throw new RuntimeException("Student Id Not Found");
        }
        return studentRepository.findById(id).orElse(null);

    }

    @Override
    public void create(Student request) {
        var checkEmail = studentRepository.findByEmail(request.getEmail());
        if(checkEmail.isPresent()){
            throw new RuntimeException("Student Email are existing");
        }
        request.setStatus("Active");
        studentRepository.save(request);
    }

    @Override
    public void update(Student request) {
        var checkId = studentRepository.findById(request.getId());
        if(checkId.isEmpty()){
            throw new RuntimeException("Student Id Not Found");
        }
        request.setStatus("Active");
        studentRepository.save(request);
    }

    @Override
    public void delete(Student request) {
        var checkId = studentRepository.findById(request.getId());
        if(checkId.isEmpty()){
            throw new RuntimeException("Student Id Not Found");
        }
        request.setStatus("Delete");
        studentRepository.save(request);
    }
}
