package com.booklog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booklog.entity.BooklogEntity;
import com.booklog.repository.JpaBooklogRepository;

@Service
public class JpaBooklogServiceImpl implements JpaBooklogService {

    @Autowired
    private JpaBooklogRepository jpaBooklogRepository;

    @Override
    public List<BooklogEntity> getBooklogList() throws Exception {
        return jpaBooklogRepository.findAllByOrderByBoardIdxDesc();
    }

}
