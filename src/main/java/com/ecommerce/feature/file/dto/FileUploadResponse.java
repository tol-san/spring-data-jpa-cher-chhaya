package com.ecommerce.feature.file.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name,
        Long size,
        String mediaType,
        String extension,
        String uri
) { }
