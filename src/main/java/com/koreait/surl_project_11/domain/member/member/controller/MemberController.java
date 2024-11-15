package com.koreait.surl_project_11.domain.member.member.controller;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.service.MemberService;
import com.koreait.surl_project_11.global.exceptions.GlobalException;
import com.koreait.surl_project_11.global.rsData.RsData;
import com.koreait.surl_project_11.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    @ResponseBody
    public RsData<Member> join(String username, String password, String nickname) {

        if (Ut.str.isBlank(username)) {
            // 예외 상황이 발생 했을 때, return 보다는 throw 가 낫다.
            // return RsData.of("400-1", "username 입력하세요.");
            throw new GlobalException("400-1", "username 입력하세요.");
        }

        if (Ut.str.isBlank(password)) {
            // return RsData.of("400-1", "password 입력하세요.");
            throw new GlobalException("400-2", "password 입력하세요.");
        }

        if (Ut.str.isBlank(nickname)) {
            // return RsData.of("400-1", "nickname 입력하세요.");
            throw new GlobalException("400-3", "nickname 입력하세요.");
        }

        return memberService.join(username, password, nickname);

    }


    @GetMapping("/testThrowIllegalArgumentException")
    @ResponseBody
    public RsData<Member> testThrowIllegalArgumentException() {
        throw new IllegalArgumentException("IllegalArgumentException");
    }
}
