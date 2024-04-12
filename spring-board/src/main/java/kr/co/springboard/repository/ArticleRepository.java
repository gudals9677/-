package kr.co.springboard.repository;

import com.querydsl.core.Tuple;
import kr.co.springboard.dto.PageRequestDTO;
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

    public Page<Article> findByParent(int parent, Pageable pageable);

    public List<Article> findByParent(int parent);

    @Query("select a, u.nick from Article a join User u on a.writer = u.uid where a.parent=:parent")
    Page<Article> findByParentWithNick(int parent, Pageable pageable);


}
