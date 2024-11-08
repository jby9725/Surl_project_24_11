package com.koreait.surl_project_11.domain.article.article.repository;

import com.koreait.surl_project_11.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // select *
    // from article
    // where id IN (1,2,3)
    // order by title DESC, id asc;
    List<Article> findByIdInOrderByTitleDescIdAsc(List<Long> ids);

    // select *
    // from article
    // where title LIKE '%안녕%';
    List<Article> findByTitleContaining(String keyword);

    // select *
    // from article
    // where title = '안녕'
    // AND body = '잘가';
    Optional<Article> findByTitleAndBody(String title, String body);

}
