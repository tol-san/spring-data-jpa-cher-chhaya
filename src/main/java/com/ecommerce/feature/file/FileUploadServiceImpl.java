package com.ecommerce.feature.file;

import com.ecommerce.feature.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {


    private final FileUploadRepository fileUploadRepository;
    private final FileUploadMapper fileUploadMapper;

    @Value("${file.storage-path}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFile(String name) {
        var file = fileUploadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File has not been found"
                ));

        fileUploadRepository.delete(file);
        try {
            Path path = Paths.get(storageLocation, name);

            boolean deleted = Files.deleteIfExists(path);

            if (!deleted) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File has not been found: " + name
                );
            }

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete file: " + name
            );
        }
    }

    @Override
    public Page<FileUploadResponse> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id" );
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<FileUpload> files = fileUploadRepository.findAll(pageRequest);
        return files.map(fileUploadMapper::toDto);
    }

    @Override
    public FileUploadResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File has been not found"
                ));
    }

    @Override
    public FileUploadResponse upload(MultipartFile file) {
        return saveFile(file);
    }



    private  FileUploadResponse saveFile(MultipartFile file) {
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString();
        Path path = Paths.get(storageLocation, fileName + "." + ext);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been field to upload"
            );
        }

        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(fileName);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("Random caption");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileUploadRepository.save(fileUpload);

        return fileUploadMapper.toDto(fileUpload);
    }
}
