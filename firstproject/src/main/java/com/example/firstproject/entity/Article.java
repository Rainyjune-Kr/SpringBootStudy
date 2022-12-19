package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // Entity라고 명시해야 DB가 인식할 수 있음.
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id // 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 Anotation.strategy를 넣어 DB에서 알아서 가져오도록 적용.
    private  Long id;

    @Column // Field를 Column에 mapping
    private String title;

    @Column
    private String content;
}
