package com.koreait.surl_project_11.domain.member.member.service;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.repository.MemberRepository;
import com.koreait.surl_project_11.global.exceptions.GlobalException;
import com.koreait.surl_project_11.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    // 리턴
    // - 이번에 생성된 멤버의 번호
    // - 게시글 생성에 대한 결과 메세지
    // - 결과 코드
    @Transactional
    // @Transactional(noRollbackFor = GlobalException.class)
    public RsData<Member> join(String username, String password, String nickname) {
        boolean present = findByUsername(username).isPresent();

        //boolean present = findByUsername(username).isPresent();
        //if (present) {
        //    throw new GlobalException("400-1", "이미 존재하는 아이디야");
        //}
        findByUsername(username).ifPresent(ignored -> {
            throw new GlobalException("400-1", "이미 존재하는 아이디야");
        });

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return RsData.of("%d번 멤버가 회원가입됨.".formatted(member.getId()), member);
    }

    @Transactional
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member getReferenceById(long id) {
        return memberRepository.getReferenceById(id);
    }

    public long count() {
        return memberRepository.count();
    }
}
