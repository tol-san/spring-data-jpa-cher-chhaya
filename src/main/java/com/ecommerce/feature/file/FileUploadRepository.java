package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    Optional<FileUpload> findByName(String name);
}