package com.booklog.dto;

import lombok.Data;

@Data
public class BooklogFileDto {
    private int idx;
    private int bookId;
    private String originalFileName;
    private String storedFilePath;
    private String fileSize;
}
