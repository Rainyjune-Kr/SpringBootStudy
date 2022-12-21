package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언 (Service 객체를 SpringBoot에 생성함)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        if (article.getId() != null){
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != article.getId()){
            log.info("잘못된 요청! id={}, article:{}", id, article.toString());
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);

        return updated;
    }

    public Article delete(long id){
        // Get article from DB
        Article target = articleRepository.findById(id).orElse(null);

        // Exception Handling
        if (target == null) {
            return null;
        }

        // Delete from DB
        articleRepository.delete(target);
        return target;
    }

    @Transactional // Handle this function as transaction.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // Convert dtos to entities
        List<Article> articleList = dtos.stream().
                map(dto -> dto.toEntity()).
                collect(Collectors.toList());

        // Save entities to DB
        articleList.stream().
                forEach(article -> articleRepository.save(article));

        // Throw Exception
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제실패")
        );

        // Return result
        return articleList;
    }
}
