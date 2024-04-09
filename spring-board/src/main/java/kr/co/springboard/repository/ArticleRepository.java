package kr.co.springboard.repository;

import kr.co.springboard.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

    //페이징 메서드 추가
    Page<Article> findByTitleContaining(String searchKeyword, Pageable pageable);

    @Query(value = "select a, u.nick from Article a join User u on a.writer = u.uid")
    List<Object[]> findAllWithNick();


}
