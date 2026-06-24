package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    FileUploadResponse upload(MultipartFile file);
    List<FileUploadResponse> uploadMultiple(MultipartFile[]  files);
    void deleteFile(String name);
}
