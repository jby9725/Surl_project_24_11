package com.koreait.surl_project_11.domain.article.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // 이거 클래스 가지고 테이블 만들 거임!
// Entity를 사용했으면, NoArgsConstructor는 있어야 함.
@Builder
// 하지만 NoArgsConstructor 추가하면 빌더가 뭐라고 하고, 그걸 피하기 위해서는 AllArgsConstructor 가 있어야 함.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id // 이 필드를 PK로 쓸거임!
    @GeneratedValue(strategy = IDENTITY) // 이거 AUTO_INCREMENT 로 만들 거임!
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body; // 이 필드의 자료형을 TEXT로 지정 할거야!
}
