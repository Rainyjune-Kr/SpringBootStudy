package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j // Logging Annotation
public class ArticleController {
    @Autowired // Springboot가 미리 생성해놓은 객체를 자동으로 연결해줌.
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") // form의 action과 같은 주소
    public String createArticle(ArticleForm form) {
        // System.out.println(form.toString()); => logging
        log.info(form.toString());

        // Convert DTO to Entity
        Article article = form.toEntity(); // Convert form's contents to Article Entity
        //System.out.println(article.toString());
        log.info(article.toString());

        // throw Entity to Repository and save to DB
        Article saved = articleRepository.save(article); // Return saved entity.
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}") // {id}는 id 값이 변수임을 명시.
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // Get entity from repository by ID
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // Map data to model from entity
        model.addAttribute("article", articleEntity);

        // throw model to article/show
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // Get all articles
        // List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
        // Iterable<Article> articleEntityList = articleRepository.findAll();
        List<Article> articleEntityList = articleRepository.findAll(); // return ArrayList

        // throw articles to view
        model.addAttribute("articleList", articleEntityList);

        // set view page
        return "articles/index"; // articles/index.mustache
    }
}
