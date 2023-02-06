package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId){
//        List<Comment> comments =  commentRepository.findByArticleId(articleId);

//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i=0;i<comments.size(); i++)
//        {
//            Comment eachComment = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(eachComment);
//            dtos.add(dto);
//        }

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article orgItem = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Error on creating comment, Cannot find article"));

        Comment comment = Comment.createComment(dto, orgItem);

        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto updateComment(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id).
                 orElseThrow(() -> new IllegalArgumentException("Error on updating comment, Comment not exists"));

        comment.patch(dto);

        Comment updated = commentRepository.save(comment);

        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Error on deleting comment, Comment not exists"));

        commentRepository.delete(comment);

        return CommentDto.createCommentDto(comment);
    }
}
