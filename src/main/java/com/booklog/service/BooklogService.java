package com.booklog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.booklog.dto.BooklogDto;
import com.booklog.dto.BooklogFileDto;

public interface BooklogService {

    List<BooklogDto> selectBooklogList() throws Exception;

    void insertBooklog(BooklogDto dto, MultipartHttpServletRequest request) throws Exception;

    BooklogDto selectBooklog(int bookId) throws Exception;

    void updateBooklog(BooklogDto dto) throws Exception;

    void deleteBooklog(int bookId) throws Exception;

    BooklogFileDto selectBooklogFileInfo(int idx, int bookId) throws Exception;

}
