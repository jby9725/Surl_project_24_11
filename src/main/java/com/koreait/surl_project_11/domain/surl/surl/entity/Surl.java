package com.koreait.surl_project_11.domain.surl.surl.entity;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koreait.surl_project_11.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Surl extends BaseTime {
    private String body;
    private String url;
    @Setter(AccessLevel.NONE) // 바깥에서 setCount로 접근하지 못하도록 막음.
    private long count;

    @ManyToOne
    @JsonIgnore // 프록시 에러 잡기
    private Member author;

    public void increaseCount() {
        count++;
    }
}
