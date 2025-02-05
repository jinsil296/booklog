package com.booklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booklog.entity.BooklogEntity;
import com.booklog.service.JpaBooklogService;

@RestController
@RequestMapping("/jpa")
public class JpaBooklogController {
    @Autowired
    private JpaBooklogService jpaBooklogService;

    @GetMapping("/board")
    public List<BooklogEntity> getBooklogList() throws Exception {
        return jpaBooklogService.getBooklogList();
    }
}
