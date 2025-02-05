package com.booklog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.booklog.entity.BooklogEntity;

public interface JpaBooklogRepository extends CrudRepository<BooklogEntity, Integer> {

    List<BooklogEntity> findAllByOrderByBoardIdxDesc();

}
