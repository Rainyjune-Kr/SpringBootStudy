package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { // CrudRepository Parameter의 첫번째 인자는 Entity, 두번째 인자는 Entity의 대표값의 type

    @Override
    ArrayList<Article> findAll();
}
