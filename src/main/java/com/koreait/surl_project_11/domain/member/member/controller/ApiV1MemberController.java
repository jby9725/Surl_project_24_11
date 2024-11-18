package com.koreait.surl_project_11.domain.member.member.controller;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.service.MemberService;
import com.koreait.surl_project_11.global.exceptions.GlobalException;
import com.koreait.surl_project_11.global.rsData.RsData;
import com.koreait.surl_project_11.standard.util.Ut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
public class ApiV1MemberController {
    private final MemberService memberService;

    @AllArgsConstructor
    @Getter
    public static class MemberJoinReqBody {
        private String username;
        private String password;
        private String nickname;
    }

    // CRUD

    // POST /api/v1/members/join
    @PostMapping("/join")
    public RsData<Member> join(
            @RequestBody MemberJoinReqBody requestBody
    ) {
        // 같은 클래스 안에 있으니 바로 접근 가능. username.
        if (Ut.str.isBlank(requestBody.username)) {
            // 예외 상황이 발생 했을 때, return 보다는 throw 가 낫다.
            // return RsData.of("400-1", "username 입력하세요.");
            throw new GlobalException("400-1", "username 입력하세요.");
        }

        if (Ut.str.isBlank(requestBody.password)) {
            // return RsData.of("400-1", "password 입력하세요.");
            throw new GlobalException("400-2", "password 입력하세요.");
        }

        if (Ut.str.isBlank(requestBody.nickname)) {
            // return RsData.of("400-1", "nickname 입력하세요.");
            throw new GlobalException("400-3", "nickname 입력하세요.");
        }

        return memberService.join(requestBody.username, requestBody.password, requestBody.nickname);

    }


    @GetMapping("/testThrowIllegalArgumentException")
    public RsData<Member> testThrowIllegalArgumentException() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }
}
