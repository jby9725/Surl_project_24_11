package com.koreait.surl_project_11.domain.article.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // 이거 클래스 가지고 테이블 만들 거임!
// Entity를 사용했으면, NoArgsConstructor는 있어야 함.
@Builder
// 하지만 NoArgsConstructor 추가하면 빌더가 뭐라고 하고, 그걸 피하기 위해서는 AllArgsConstructor 가 있어야 함.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // @CreatedDate 와 @LastModifiedDate 쓸려면 붙여야 함.
public class Article {

    @Id // 이 필드를 PK로 쓸거임!
    @GeneratedValue(strategy = IDENTITY) // 이거 AUTO_INCREMENT 로 만들 거임!
    private long id;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body; // 이 필드의 자료형을 TEXT로 지정 할거야!
}
