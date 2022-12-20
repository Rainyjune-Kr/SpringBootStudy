package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    // Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){ //RequestBody를 써야 Post 요청 내에 실려진 내용을 사용할 수 있음.
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // Generate Entity
        Article article = dto.toEntity();
        log.info("id: {}, article:{}", id, article.toString());

        // Inquiry Target Entity
        Article target = articleRepository.findById(id).orElse(null);

        // Handling Exception
        if (target == null || id != dto.toEntity().getId()) {
            // return status 400
            log.info("잘못된 요청. id={}, article:{}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Update and response
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // Get article from DB
        Article article = articleRepository.findById(id).orElse(null);

        // Exception Handling
        if (article == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Delete from DB
        articleRepository.delete(article);

        // Return Response
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
