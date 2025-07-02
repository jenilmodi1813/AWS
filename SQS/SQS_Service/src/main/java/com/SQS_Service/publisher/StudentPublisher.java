package com.SQS_Service.publisher;

import com.SQS_Service.dto.CreateStudent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
//import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;

@Component
@RequiredArgsConstructor
public class StudentPublisher {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String queueUrl;

    public void sendStudentMessage(CreateStudent createStudent) {
        try {
            // ✅ Convert to JSON string
            String message = objectMapper.writeValueAsString(createStudent);

            // ✅ Build and send message
            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(message)
                    .build();

            sqsClient.sendMessage(request);
            System.out.println("✅ Message sent to SQS: " + message);

        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
