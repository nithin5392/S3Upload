package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class S3UploadController {
    
    private final S3DocumentUploader uploader;
    
    public S3UploadController(S3Client s3Client, @Value("${S3_BUCKET_NAME:nithin-edu-test}") String bucketName) {
        this.uploader = new S3DocumentUploader(bucketName, s3Client);
    }

    @PostMapping("/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);
        
        String s3Key = "documents/" + file.getOriginalFilename();
        uploader.uploadDocument(tempFile.getAbsolutePath(), s3Key);
        
        tempFile.delete();
        return "File uploaded successfully: " + s3Key;
    }
}