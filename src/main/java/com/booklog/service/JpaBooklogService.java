package com.booklog.service;

import java.util.List;

import com.booklog.entity.BooklogEntity;

public interface JpaBooklogService {

    List<BooklogEntity> getBooklogList() throws Exception;

}
