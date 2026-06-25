package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUploadMapper {
    @Value("${file.base-uri}")
    private String baseUri;
    FileUploadResponse toDto(FileUpload fileUpload) {
        return FileUploadResponse.builder()
                .name(fileUpload.getName())
                .mediaType(fileUpload.getMediaType())
                .size(fileUpload.getSize())
                .extension(fileUpload.getExtension())
                .uri(baseUri + fileUpload.getName() + "." + fileUpload.getExtension())
                .build();
    }
}
