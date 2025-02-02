package com.booklog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booklog.dto.BooklogDto;
import com.booklog.dto.BooklogFileDto;

@Mapper
public interface BooklogMapper {

    List<BooklogDto> selectBooklogList() throws Exception;

    void insertBooklog(BooklogDto dto) throws Exception;

    BooklogDto selectBooklog(int bookId) throws Exception;

    void updateBooklog(BooklogDto dto) throws Exception;

    void deleteBooklog(int bookId) throws Exception;

    void insertBooklogFileList(List<BooklogFileDto> fileInfoList) throws Exception;

    List<BooklogFileDto> selectBooklogFileList(int bookId) throws Exception;

    BooklogFileDto selectBooklogFileInfo(@Param("idx") int idx, @Param("bookId") int bookId);

}
