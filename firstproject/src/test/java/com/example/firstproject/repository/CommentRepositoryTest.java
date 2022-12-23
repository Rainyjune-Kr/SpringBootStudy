package com.example.firstproject.repository;

import ch.qos.logback.core.read.ListAppender;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA 연동 Test를 알림
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 2번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 2L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(2L, "TITLE2", "CONTENT2");
            Comment commentExpected = new Comment(2L, article, "USER2", "REPLY 2");
            Comment commentExpected2 = new Comment(3L, article, "USER3", "REPLY 3");
            List<Comment> expected = Arrays.asList(commentExpected, commentExpected2);

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }

        /* Case 1: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(1L, "TITLE1", "CONTENT1");
            Comment commentExpected = new Comment(1L, article, "USER1", "REPLY 1");
            Comment commentExpected2 = new Comment(4L, article, "USER4", "REPLY 4");
            Comment commentExpected3 = new Comment(5L, article, "USER5", "REPLY 5");
            List<Comment> expected = Arrays.asList(commentExpected, commentExpected2, commentExpected3);

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        String nickname = "USER1";

        List<Comment> comments = commentRepository.findByNickname(nickname);

        Article article = new Article(1L, "TITLE1", "CONTENT1");
        Comment expectedComment = new Comment(1L, article, "USER1", "REPLY 1");
        List<Comment> expected = Arrays.asList(expectedComment);

        assertEquals(comments.toString(), expected.toString());
    }
}