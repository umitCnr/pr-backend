package com.pr.project_backend.service;

import io.minio.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient client;

    @Value("${minio.bucketName}")
    private String bucketName;


    public String uploadImageMinio(MultipartFile file, String fileName) throws IOException {
        try (InputStream stream = file.getInputStream()) {

            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(stream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            System.out.println("Image uploaded successfully to MinIO: " + fileName);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Failed to upload image to MinIO: " + e.getMessage(), e);
        }
    }

    public String  getAllFile(String fileName) {
        try (InputStream inputStream = client.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        )) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] fileBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (Exception e) {
            System.out.println("Resim getirilemedi: " + e);
            return null;
        }
    }
}
