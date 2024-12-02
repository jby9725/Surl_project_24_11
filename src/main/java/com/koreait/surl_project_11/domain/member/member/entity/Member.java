package com.koreait.surl_project_11.domain.member.member.entity;

import com.koreait.surl_project_11.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static lombok.AccessLevel.PROTECTED;

@Entity // 이거 클래스 가지고 테이블 만들 거임!
// Entity를 사용했으면, NoArgsConstructor는 있어야 함.
@Builder
// 하지만 NoArgsConstructor 추가하면 빌더가 뭐라고 하고, 그걸 피하기 위해서는 AllArgsConstructor 가 있어야 함.
@Getter
@Setter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class) // @CreatedDate 와 @LastModifiedDate 쓸려면 붙여야 함.
@ToString
public class Member extends BaseTime {
    @Column(unique = true)
    private String username;
    //    @Column(columnDefinition = "TEXT") // 이 필드의 자료형을 TEXT로 지정 할거야!
    private String password;
    private String nickname;
    @Column(unique = true)
    private String refreshToken;

    public String getName() {
        return nickname;
    }
}
