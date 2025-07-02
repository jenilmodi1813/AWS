package com.SQS_Service.controller;

import com.SQS_Service.dto.CreateStudent;
import com.SQS_Service.publisher.StudentPublisher;
import com.SQS_Service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Std")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentPublisher studentPublisher;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody CreateStudent createStudent) {
        studentPublisher.sendStudentMessage(createStudent);
        return studentService.createStudent(createStudent);
    }
}
