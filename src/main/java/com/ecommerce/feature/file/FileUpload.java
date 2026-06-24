package com.ecommerce.feature.file;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter @Setter
@RequiredArgsConstructor
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String caption;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String mediaType;
}
