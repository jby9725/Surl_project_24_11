package com.koreait.surl_project_11;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Surl {

    private long id;
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime modifyDate = LocalDateTime.now();
    private String body;
    private String url;

    // 바깥에서 setCount로 접근하지 못하도록 막음.
    @Setter(AccessLevel.NONE)
    private long count;

    public void increaseCount() {
        count++;
    }
}