# AWS S3 Document Upload Project

## Setup

### Local Development
1. Copy `application-template.properties` to `application.properties`
2. Update credentials in `application.properties`

### EC2 Deployment (Recommended)
1. Create IAM role with S3 permissions
2. Attach role to EC2 instance
3. Set environment variables:
   ```bash
   export AWS_REGION=us-east-1
   export S3_BUCKET_NAME=your-bucket-name
   ```
4. Application will use EC2 instance profile automatically

## Usage

### Web Interface
Start the application:
```bash
mvn spring-boot:run
```

Open browser and go to: http://localhost:8080

### REST API
Upload a document via POST request:
```bash
curl -X POST -F "file=@document.pdf" http://localhost:8080/api/upload
```

### Direct Java Usage

```java
import org.example.S3DocumentUploader;

S3DocumentUploader uploader = new S3DocumentUploader("your-bucket-name", Region.US_EAST_1);
uploader.

uploadDocument("local/file/path.pdf","s3/key/path.pdf");
```

## Required IAM Permissions
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": "s3:PutObject",
            "Resource": "arn:aws:s3:::your-bucket-name/*"
        }
    ]
}
```