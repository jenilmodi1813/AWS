package com.services.s3Service.service;

import com.services.s3Service.config.S3Config;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Serivce {

    private final S3Client s3Client;


    public ResponseEntity<?> createBucket(String bucketName) {

        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .createBucketConfiguration(
                        CreateBucketConfiguration.builder()
                                .locationConstraint("ap-south-1")
                                .build()
                )
                .build();
        s3Client.createBucket(createBucketRequest);
        return ResponseEntity.ok().body("Bucket Created : ");
    }

    public ResponseEntity<?> upload(String bucketName, MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        File tempFile = File.createTempFile("s3-upload-", key);
        file.transferTo(tempFile);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(request, RequestBody.fromFile(tempFile));
        tempFile.delete();
        return ResponseEntity.ok().body("File Uploaded :");
    }

    public ResponseEntity<?> deleteFile(String bucketName, String key) {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(request);
        return ResponseEntity.ok().body("File Deleted :");
    }

    public ResponseEntity<?> deleteBucket(String bucketName) {
        DeleteBucketRequest request = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3Client.deleteBucket(request);
        return ResponseEntity.ok().body("Bucket Deleted :");
    }

    public ResponseEntity<?> getFile(String bucketName, String key) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(request);
        byte[] byteArray = responseBytes.asByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArray);
    }
}
