package com.koreait.surl_project_11.domain.article.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // 이거 클래스 가지고 테이블 만들 거임!
@Builder
@Getter
public class Article {

    @Id // 이 필드를 PK로 쓸거임!
    @GeneratedValue(strategy = IDENTITY) // 이거 AUTO_INCREMENT 로 만들 거임!
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body; // 이 필드의 자료형을 TEXT로 지정 할거야!
}
