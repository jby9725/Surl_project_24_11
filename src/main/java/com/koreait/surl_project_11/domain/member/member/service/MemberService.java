package com.koreait.surl_project_11.domain.member.member.service;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.repository.MemberRepository;
import com.koreait.surl_project_11.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    // 리턴
    // - 이번에 생성된 게시글의 번호
    // - 게시글 생성에 대한 결과 메세지
    // - 결과 코드
    public RsData<Member> join(String username, String password, String nickname) {
        boolean present = findByUsername(username).isPresent();

        if (present) {
            return RsData.of("400-1", "이미 존재하는 아이디입니다.", Member.builder().build());
        }

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return RsData.of("%d번 멤버가 회원가입됨.".formatted(member.getId()), member);
    }

    private Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
