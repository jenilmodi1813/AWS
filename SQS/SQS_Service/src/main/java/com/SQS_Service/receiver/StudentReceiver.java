package com.SQS_Service.receiver;

import com.SQS_Service.dto.CreateStudent;
import com.SQS_Service.service.StudentService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentReceiver {

    @Autowired
    private StudentService studentService;

    @SqsListener("my-first-queue")
    public void handleMessage(CreateStudent createStudent) {
        studentService.createStudent(createStudent);
        System.out.println("Received from SQS: " + createStudent.toString());
    }
}
