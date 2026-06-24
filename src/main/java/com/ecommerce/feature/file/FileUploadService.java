package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUploadResponse upload(MultipartFile file);
}
