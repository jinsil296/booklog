package com.booklog.dto;

import java.util.List;

import lombok.Data;

@Data
public class BooklogDto {
    private int bookId;
    private String title;
    private String contents;
    private String author;
    private String publisher;
    private String publishedDate;
    private String isbn;
    private String description;
    private String createdAt;
    private String updatedAt;
    // 첨부 파일 정보를 저장할 필드를 추가
    private List<BooklogFileDto> fileInfoList;
}
