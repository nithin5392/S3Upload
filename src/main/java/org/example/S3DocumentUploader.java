package org.example;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

public class S3DocumentUploader {
    private final S3Client s3Client;
    private final String bucketName;

    public S3DocumentUploader(String bucketName, S3Client s3Client) {
        this.bucketName = bucketName;
        this.s3Client = s3Client;
    }

    public void uploadDocument(String filePath, String s3Key) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        s3Client.putObject(request, RequestBody.fromFile(Path.of(filePath)));
        System.out.println("Document uploaded successfully: " + s3Key);
    }


}