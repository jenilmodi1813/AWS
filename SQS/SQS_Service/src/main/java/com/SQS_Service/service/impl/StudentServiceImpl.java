package com.SQS_Service.service.impl;

import com.SQS_Service.dto.CreateStudent;
import com.SQS_Service.entities.Student;
import com.SQS_Service.repositories.StudentRepo;
import com.SQS_Service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public ResponseEntity<?> createStudent(CreateStudent createStudent) {

        Student student  = Student.builder()
                .fname(createStudent.getFname())
                .lname(createStudent.getLname())
                .email(createStudent.getEmail())
                .password(createStudent.getPassword())
                .build();
        studentRepo.save(student);
        return ResponseEntity.ok().body("Student Created : ");
    }
}
