package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {


    @Value("${file.storage-path}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    @Override
    public FileUploadResponse upload(MultipartFile file) {
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID() + "." + ext;
        Path path = Paths.get(storageLocation + fileName);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been field to upload"
            );
        }
        return FileUploadResponse.builder()
                .name(fileName)
                .mediaType(file.getContentType())
                .size(file.getSize())
                .uri(baseUri+fileName)
                .build();
    }
}
