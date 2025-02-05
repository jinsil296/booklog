package com.booklog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_jpa_file")
@NoArgsConstructor
@Data
public class BooklogFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String storedFilePath;

    @Column(nullable = false)
    private long fileSize;

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    private LocalDateTime createdDt = LocalDateTime.now();

    private String updatorId;

    private LocalDateTime updatedDt;
}
