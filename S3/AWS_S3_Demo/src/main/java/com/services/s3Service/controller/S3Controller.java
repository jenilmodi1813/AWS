package com.services.s3Service.controller;

import com.services.s3Service.service.S3Serivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Serivce s3Serivce;

    @PostMapping("/create-bucket")
    public ResponseEntity<?> createBucket(@RequestParam String bucketName){
        return s3Serivce.createBucket(bucketName);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam String bucketName,
            @RequestParam MultipartFile file) throws IOException {
        return s3Serivce.upload(bucketName,file);
    }

    @GetMapping("/getFile")
    public ResponseEntity<?> getFile(
            @RequestParam String bucketName,
            @RequestParam String key
    ){
        return s3Serivce.getFile(bucketName,key);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(
            @RequestParam String bucketName,
            @RequestParam String key
    ){
        return s3Serivce.deleteFile(bucketName,key);
    }

    @DeleteMapping("/delete-bucket")
    public ResponseEntity<?> deleteBucket(@RequestParam String bucketName){
        return s3Serivce.deleteBucket(bucketName);
    }
}
