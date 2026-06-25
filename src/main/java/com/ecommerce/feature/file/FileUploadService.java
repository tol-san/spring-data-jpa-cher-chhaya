package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    Page<FileUploadResponse> findAll(int pageNumber, int pageSize);
    FileUploadResponse findByName(String name);
    FileUploadResponse upload(MultipartFile file);
    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);
    void deleteFile(String name);
}
