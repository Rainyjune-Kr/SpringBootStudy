package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne // 댓글 Entity 여러개가 하나의 Article과 관계있다.
    @JoinColumn(name = "article_id") // "article_id" 컬럼에 Article의 대표값을 지정
    private  Article article;

    private  String nickname;
    private  String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if (dto.getId() != null)
            throw new IllegalArgumentException("Error on creating comment. comment id exists");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("Error on creating comment. article id is not valid");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // throw exception
        if (this.id != dto.getId())
            throw new IllegalArgumentException("Invalid comment id");

        // patch object
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
