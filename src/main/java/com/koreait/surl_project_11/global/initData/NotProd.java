package com.koreait.surl_project_11.global.initData;

import com.koreait.surl_project_11.domain.article.article.entity.Article;
import com.koreait.surl_project_11.domain.article.article.service.ArticleService;
import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.service.MemberService;
import com.koreait.surl_project_11.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ApplicationRunner initNotProd() {
        return args -> {
            self.work1();
            work2(); //this. 가 생략 되어 있다. 이러면 트랜잭션 적용을 못 받는다.
//            self.work3();
//            self.work4();
//            self.work5();

            self.joinMember();
        };
    }

    @Transactional
    public void work1() {

        // articleRepository.deleteAll(); // AUTO_INCREMENT 값이 초기화 되지 않는다.
        // DB 쪽에서 직접 TRUNCATE : AUTO_INCREMENT 값이 초기화 된다.

        if (articleService.count() > 0) return;

        Article article1 = articleService.write("제목 1", "내용 1").getData();
        Article article2 = articleService.write("제목 2", "내용 2").getData();

        // 하나 수정하려고 할 때
        article2.setTitle("제목 2-2");

        // 하나 삭제하려고 할 때
        articleService.delete(article1);
    }


    @Transactional
    public void work2() {
        // Optional : List와 비슷하다
        // 차이점
        //	- List : 0 ~ N 개 가능
        //	- Optional : 0 ~ 1 개 가능
        Optional<Article> articleOptional = articleService.findById(1L);

        List<Article> articleList = articleService.findAll();

    }

    @Transactional
    public void work3() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        // articleRepository.findByIdInOrderByTitleDescIdAsc(ids); 또는
//        articleRepository.findByIdInOrderByTitleDescIdAsc(List.of(1L, 2L, 3L));
    }

    @Transactional
    public void work4() {
        String keyword = "안녕";
//        articleRepository.findByTitleContaining(keyword);
    }

    @Transactional
    public void work5() {
        String title = "안녕";
        String body = "잘가";
//        articleRepository.findByTitleAndBody(title, body);
    }

    @Transactional
    public void joinMember() {

        Member member1 = memberService.join("user1", "1234", "유저1").getData();
        RsData<Member> member2 = memberService.join("user2", "1234", "유저2");

//        try {
//            RsData<Member> joinRs = memberService.join("user2", "1234", "유저 2");
//        } catch (GlobalException e) {
//            System.out.println("e.getMsg() : " + e.getRsData().getMsg());
//            System.out.println("e.getStatusCode() : " + e.getRsData().getStatusCode());
//        }

        System.out.println("member3.getMsg() : " + member2.getMsg());
        System.out.println("member3.getStatusCode() : " + member2.getStatusCode());

    }

}