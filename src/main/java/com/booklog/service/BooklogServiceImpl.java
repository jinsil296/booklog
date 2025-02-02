package com.booklog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.booklog.common.FileUtils;
import com.booklog.dto.BooklogDto;
import com.booklog.dto.BooklogFileDto;
import com.booklog.mapper.BooklogMapper;

@Service
public class BooklogServiceImpl implements BooklogService {

    @Autowired
    private BooklogMapper booklogMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<BooklogDto> selectBooklogList() throws Exception {
        return booklogMapper.selectBooklogList();
    }

    @Override
    public void insertBooklog(BooklogDto dto, MultipartHttpServletRequest request) throws Exception {
        booklogMapper.insertBooklog(dto);
        try {
            // 첨부 파일을 디스크에 저장하고, 첨부 파일 정보를 반환
            List<BooklogFileDto> fileInfoList = fileUtils.parseFileInfo(dto.getBookId(), request);

            // 첨부 파일 정보를 DB에 저장
            if (!CollectionUtils.isEmpty(fileInfoList)) {
                booklogMapper.insertBooklogFileList(fileInfoList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public BooklogDto selectBooklog(int bookId) throws Exception {

        BooklogDto dto = booklogMapper.selectBooklog(bookId);
        List<BooklogFileDto> files = booklogMapper.selectBooklogFileList(bookId);
        dto.setFileInfoList(files);

        return dto;
    }

    @Override
    public void updateBooklog(BooklogDto dto) throws Exception {
        booklogMapper.updateBooklog(dto);
    }

    @Override
    public void deleteBooklog(int bookId) throws Exception {
        booklogMapper.deleteBooklog(bookId);
    }

    @Override
    public BooklogFileDto selectBooklogFileInfo(int idx, int bookId) throws Exception {
        return booklogMapper.selectBooklogFileInfo(idx, bookId);
    }

}
