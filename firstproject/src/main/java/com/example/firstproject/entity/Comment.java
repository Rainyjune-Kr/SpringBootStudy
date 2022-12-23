package com.example.firstproject.entity;

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

}
