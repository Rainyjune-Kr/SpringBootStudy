package com.example.firstproject.api;

import com.example.firstproject.annotation.RunningTime;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // throw to service
        List<CommentDto> dtos = commentService.comments(articleId);

        // Return response
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId,
                                                     @RequestBody CommentDto dto){
        CommentDto commentDto =  commentService.createComment(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id,
                                                     @RequestBody CommentDto dto){
        CommentDto updatedDto =  commentService.updateComment(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 댓글 삭제
//    @RunningTime
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){
        CommentDto deletedDto =  commentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
