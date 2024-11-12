package com.koreait.surl_project_11.global.initData;

import com.koreait.surl_project_11.domain.article.article.entity.Article;
import com.koreait.surl_project_11.domain.article.article.service.ArticleService;
import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@Profile("!prod") // !prod == dev or test 일 때 실행하겠다.
@Configuration // 빈이 자동화되서 실행된다 >> 이 어노테이션이 붙어있어야 실행한다.
@RequiredArgsConstructor
public class NotProd {
    // 트랜잭션을 제대로 사용 하기 위해 아래와 같은 작업이 필요 하다. 클래스 객체를 가지고 있어야 하는 것과 this의 차이를 모르면 그냥 외우자..
    // 상세 설명 :
    // this를 통한 객체 내부에서의 메서드 호출은 @Transactional를 작동시키지 않는다.
    // 외부 객체에 의한 메서드 호출은 @Transactional이 작동한다.
    // @Lazy, @Autowired 조합은 this의 외부 호출 모드 버전 self를 얻을 수 있다.
    // self를 통한 메서드 호출은 @Transactional 작동 가능.
    @Lazy
    @Autowired
    private NotProd self;

    private final ArticleService articleService;
    private final MemberService memberService;

//    @Autowired
//    private ArticleRepository articleRepository;

    @Bean // 빈을 등록한다. 스트링부트에. 개발자가 new 하지 않아도 스프링부트가 직접 관리하는 객체. 실행될때 자동으로 올라간다.
    @Order(4)
    public ApplicationRunner initNotProd() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if (articleService.count() > 0) return;

        Member memberUser1 = memberService.findByUsername("user1").get();
        Member memberUser2 = memberService.findByUsername("user2").get();

        Article article1 = articleService.write(memberUser1, "제목 1", "내용 1").getData();
        Article article2 = articleService.write(memberUser1, "제목 2", "내용 2").getData();

        Article article3 = articleService.write(memberUser2, "제목 3", "내용 3").getData();
        Article article4 = articleService.write(memberUser2, "제목 4", "내용 4").getData();
    }
}