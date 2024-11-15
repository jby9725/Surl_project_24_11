package com.koreait.surl_project_11.domain.member.member.controller;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.service.MemberService;
import com.koreait.surl_project_11.global.exceptions.GlobalException;
import com.koreait.surl_project_11.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    @ResponseBody
    public RsData join(String username, String password, String nickname) {

        if(username == null || username.isBlank()) {
            // return RsData.of("400-1", "username 입력하세요.");
            throw new GlobalException("400-1", "username 입력하세요.");
        }

        if(password == null || password.isBlank()) {
            // return RsData.of("400-1", "password 입력하세요.");
            throw new GlobalException("400-2", "password 입력하세요.");
        }

        if(nickname == null || nickname.isBlank()) {
            // return RsData.of("400-1", "nickname 입력하세요.");
            throw new GlobalException("400-3", "nickname 입력하세요.");
        }

        RsData<Member> joinRs = memberService.join(username, password, nickname);

        return joinRs;
    }
}
