package com.SQS_Service.service;

import com.SQS_Service.dto.CreateStudent;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<?> createStudent(CreateStudent createStudent);
}
