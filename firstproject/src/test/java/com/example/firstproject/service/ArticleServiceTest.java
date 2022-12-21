package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // This class will be tested using SpringBoot
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // Expected
        Article article = new Article(1L, "TITLE1", "CONTENT1");
        Article article2 = new Article(2L, "TITLE2", "CONTENT2");
        Article article3 = new Article(3L, "TITLE3", "CONTENT3");
        List<Article> expectedList = new ArrayList<Article>(Arrays.asList(article, article2, article3));

        // Real
        List<Article> articleList = articleService.index();

        // Compare
        assertEquals(expectedList.toString(), articleList.toString());
    }

    @Test
    void show_success_input_exist_id() {
        long id = 1L;
        // Expected
        Article expected = new Article(1L, "TITLE1", "CONTENT1");

        // Real
        Article article = articleService.show(id);

        // Compare
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_failed_input_not_exist_id(){
        long id = 5L;
        // Expected
        Article expected = null;

        // Real
        Article article = articleService.show(id);

        // Compare
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success_title_content_dto() {
        String title = "TITLE4";
        String content = "CONTENT4";
        ArticleForm dto = new ArticleForm(null, title, content);

        // Expected
        Article expected = new Article(4L, title, content);

        // Real
        Article article = articleService.create(dto);

        // Compare
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_failed_id_included_dto() {
        Long id = 1L;
        String title = "TITLE4";
        String content = "CONTENT4";
        ArticleForm dto = new ArticleForm(id, title, content);

        // Expected
        Article expected = null;

        // Real
        Article article = articleService.create(dto);

        // Compare
        assertEquals(expected, article);
    }
}