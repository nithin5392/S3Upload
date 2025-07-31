FROM amazoncorretto:11

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENV AWS_REGION=us-east-1
ENV S3_BUCKET_NAME=nithin-edu-test

ENTRYPOINT ["java", "-jar", "app.jar"]