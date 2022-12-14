package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // Entity라고 명시해야 DB가 인식할 수 있음.
@AllArgsConstructor
@ToString
public class Article {
    @Id // 대표값 지정
    @GeneratedValue // 자동 생성 Anotation.
    private  Long id;

    @Column // Field를 Column에 mapping
    private String title;

    @Column
    private String content;
}
